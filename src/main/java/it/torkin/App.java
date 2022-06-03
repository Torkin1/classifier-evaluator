package it.torkin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import it.torkin.entity.Buggyness;
import it.torkin.entity.ClassifierImplementation;
import it.torkin.entity.FeatureSelectionApproach;
import it.torkin.entity.RichEvaluation;
import it.torkin.evaluator.WalkForward;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Classifier;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class App 
{
    private static final Logger logger = Logger.getLogger(App.class.getName());
    
    /** data loaded from given dataset file. It is assumed that the dataset is a time serie*/
    private static Instances dataset;
    private static final Set<ClassifierImplementation> requestedClassifiers = EnumSet.noneOf(ClassifierImplementation.class);
    private static final Set<Classifier> classifiers = new HashSet<>();
    private static final Set<FeatureSelectionApproach> requestedFeatureSelectionApproaches = EnumSet.noneOf(FeatureSelectionApproach.class);
    private static boolean isFeatureSearchBackward;  
        
    private static void parseArgs(String[] args) throws Exception{

        StringTokenizer tokenizer;
        String token;
        
        // args[0]: path to file containing the dataset
        File datasetFile = new File(args[0]);
        if (datasetFile.exists()){
            dataset = new DataSource(datasetFile.getAbsolutePath()).getDataSet();
            dataset.setClassIndex(Buggyness.getFeatureIndex(dataset.numAttributes()));
        } else {
            throw new UnableToFindDatasetFileException(datasetFile.getName());
        }

        // args[1]: comma-separated list of classifer names to evaluate
        tokenizer = new StringTokenizer(args[1], ",");
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            requestedClassifiers.add(ClassifierImplementation.getClassifier(token));
        }

        // args[2]: comma-separated list of feature selection approaches
        tokenizer = new StringTokenizer(args[2], ",");
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();    
            requestedFeatureSelectionApproaches.add(FeatureSelectionApproach.getFeatureSelectionApproach(token));
        }

        // args[3]: Sense of feature search.
        // Can be "backward" or "forward".
        // Ignored if no feature selection approach is specified (search sense must be specified anyway)
        switch(args[3]){
            case "forward":{ 
                isFeatureSearchBackward = false;
                break;
            }
            case "backward":{
                isFeatureSearchBackward = true;
                break;
            }
            default: throw new IllegalArgumentException(String.format("Unrecognized feature search sense: %s", args[3]));
        }
    }

    private static void prepareClassifiers() throws UnableToPrepareClassifiersException{
        
        String name = null;
        try {
            for (ClassifierImplementation c : requestedClassifiers){
                name = c.getName();
                classifiers.add(c.getImplementation().getConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            
            throw new UnableToPrepareClassifiersException(name, e);
        }
    }
    
    private static void printEvaluations(List<RichEvaluation> evaluations) throws UnableToPrintEvaluationsException {

        String msg;

        String outputDirName = "evaluations";
        String outputName = outputDirName
         + File.separator 
         + "evaluation_" 
         + dataset.relationName() 
         + "_" + requestedClassifiers.toString() 
         + "_" + requestedFeatureSelectionApproaches.toString() + "-" + ((isFeatureSearchBackward)? "backward" : "forward") 
         + ".csv";
        File outputDir = new File(outputDirName);
        File output = new File(outputName);
        
        // dump evaluations in csv file
        outputDir.mkdir();
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(output), CSVFormat.DEFAULT)){

                // print headers
                printer.printRecord(
                    "dataset name"
                    ,"classifier"
                    ,"dataset stratified?"
                    ,"#trainingReleases"
                    ,"%trainingRelease"
                    ,"%defective in training"
                    ,"%defective in testing"
                    ,"#TPs"
                    ,"#FPs"
                    ,"#TNs"
                    ,"#FNs"
                    ,"Precision"
                    ,"Recall"
                    ,"AUC"
                    ,"Kappa"
                );

                // prints values
                int positiveIndex = dataset.classAttribute().indexOfValue(Buggyness.YES.getNominalValue());
                for (RichEvaluation e : evaluations){
                    printer.printRecord(
                        e.getDatasetName()
                        ,e.getClassifier().getName()
                        ,e.isDatasetStratified()
                        ,e.getNumOfTrainingReleases()
                        ,e.getTrainingPercentage()
                        ,e.getDefectiveInTrainingPercentage()
                        ,e.getDefectiveInTestingPercentage()
                        ,e.numTruePositives(positiveIndex)
                        ,e.numFalsePositives(positiveIndex)
                        ,e.numTrueNegatives(positiveIndex)
                        ,e.numFalseNegatives(positiveIndex)
                        ,e.precision(positiveIndex)
                        ,e.recall(positiveIndex)
                        ,e.areaUnderPRC(positiveIndex)  // we use AUC of precision - recall curve
                        ,e.kappa()
                    );
                }

                msg = String.format("evaluation data available at %s", output);
                logger.info(msg);

            } catch (IOException e){
                throw new UnableToPrintEvaluationsException(e);
            }
        }

    public static void main( String[] args ) throws Exception 
    {
        Instances evaluationDataset;
        Classifier classifier;
        WalkForward walkForward;
        List<RichEvaluation> evaluations = new ArrayList<>();
        RichEvaluation evaluation;
        String msg; // for logging
        
        parseArgs(args);

        // prepare classifiers
        prepareClassifiers();

        evaluationDataset = dataset;
        // change working datatset with filtered one obtained by filter feature selection approach if requested
        if (requestedFeatureSelectionApproaches.contains(FeatureSelectionApproach.FILTER)){
            AttributeSelection filter = new AttributeSelection();
            GreedyStepwise search = new GreedyStepwise();
            ASEvaluation eval = new CfsSubsetEval();
            search.setSearchBackwards(isFeatureSearchBackward);
            filter.setInputFormat(dataset);
            filter.setSearch(search);
            filter.setEvaluator(eval);
            evaluationDataset = Filter.useFilter(dataset, filter);
        }

        // for each classifier, gets one evaluation for each walkforward iteration 
        for (ClassifierImplementation requestedClassifier : requestedClassifiers){

            msg = String.format("evaluating classifier: %s", requestedClassifier.getName());
            logger.info(msg);
            classifier = requestedClassifier.getImplementation().getConstructor().newInstance();
            // change classifer with wrapper feature selection meta-classifier if requested
            if (requestedFeatureSelectionApproaches.contains(FeatureSelectionApproach.WRAPPER)){
                AttributeSelectedClassifier wrapper = new AttributeSelectedClassifier();
                GreedyStepwise search = new GreedyStepwise();
                ASEvaluation eval = new CfsSubsetEval();
                search.setSearchBackwards(isFeatureSearchBackward);
                wrapper.setClassifier(classifier);
                wrapper.setEvaluator(eval);
                wrapper.setSearch(search);
                classifier = wrapper;
            }
            
            walkForward = new WalkForward(classifier, evaluationDataset);
            while(walkForward.hasNext()){
                evaluation = walkForward.next();
                // TODO: set feature selection approach in evaluation object
                // TODO: set whetever dataset was stratified before evaluation or not
                evaluation.setDatasetName(dataset.relationName());
                evaluation.setClassifier(requestedClassifier);
                evaluations.add(evaluation);
            }
        }
        printEvaluations(evaluations);
    }

}
