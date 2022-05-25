package it.torkin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class App 
{
    private static Logger logger = Logger.getLogger(App.class.getName());
    
    /** data loaded from given dataset file*/
    private static Instances dataset;
    private static final Set<Classifiers> requestedClassifiers = EnumSet.noneOf(Classifiers.class);
    private static final Set<Classifier> classifiers = new HashSet<>();  
        
    private static void parseArgs(String[] args) throws Exception{

        // args[0]: path to file containing the dataset
        File datasetFile = new File(args[0]);
        if (datasetFile.exists()){
            dataset = new DataSource(datasetFile.getAbsolutePath()).getDataSet();
        } else {
            throw new UnableToFindDatasetFileException(datasetFile.getName());
        }

        // args[1]: comma-separated list of classifer names to evaluate
       StringTokenizer tokenizer = new StringTokenizer(args[1], ",");
       String token;
       while (tokenizer.hasMoreTokens()) {
           token = tokenizer.nextToken();
           try {
               requestedClassifiers.add(Classifiers.getClassifier(token));
           } catch (NoSuchElementException e) {
               String msg = String.format("Ignoring unrecognized classifer name %s", token);
               logger.log(Level.WARNING, msg, e);
           }
       }
    }

    private static void prepareClassifiers() throws UnableToPrepareClassifiersException{
        
        String name = null;
        try {
            for (Classifiers c : requestedClassifiers){
                name = c.getName();
                classifiers.add(c.getImplementation().getConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            
            throw new UnableToPrepareClassifiersException(name, e);
        }
    }
    
    public static void main( String[] args ) throws Exception 
    {
        parseArgs(args);

        // prepare classifiers
        prepareClassifiers();
        Set<Classifier> test = classifiers;
        int test2 = 0;

        // TODO: prepare evaluation matrix

        // TODO: populate evaluation matrix with evaluations from walkForward iterations and calculate average

        // TODO: dump evaluations in csv file
        
    }
}
