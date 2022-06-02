package it.torkin.evaluator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.torkin.entity.ProjectResourceInstances;
import it.torkin.entity.RichEvaluation;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.filters.Filter;

/**
 * WalkForward implementation algorithm.
 * Such classifier evaluation algorithm can be imagined as a generator of evaluations.
 * It can be used to walk through all evaluations calling its next method.
 * It is assumed that the dataset is a time serie, and that the labeling class is at the last column of the dataset
 */
public class WalkForward implements Iterator<Evaluation>{
    
    // cross-iteration state

    /**classifier to evaluate */
    private Classifier classifier;
    /**time ordered dataset */
    private Instances data;
    
    // current iteration state
    private Instances train;
    private Instances test;
    private int i;
    private int numOfReleases;

    public WalkForward(Classifier classifier, Instances dataset){
        this.classifier = classifier;
        setData(dataset);
        this.train = new Instances(data, data.size());
        this.test = new Instances(data, 1);
        this.i = 1;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public void setData(Instances data) {
        this.data = data;
        this.numOfReleases = new ProjectResourceInstances(data).getNumOfReleases();
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public Instances getData() {
        return data;
    }

    public Instances getTrain() {
        return train;
    }

    public Instances getTest() {
        return test;
    }

    public int getI() {
        return i;
    }
        
    @Override
    public boolean hasNext() {
        return i < numOfReleases;
    }
    
    @Override
    public RichEvaluation next() {
        
        RichEvaluation eval;
        
        if (i < 1 || i >= numOfReleases){
            throw new NoSuchElementException();
        }
        
        try {
            
            // updates training set with (i-i)-release entries
            train.addAll(Filter.useFilter(data, new ReleaseFilter(data, i - 1)));

            // takes i-release as the testing set
            test.clear();
            test.addAll(Filter.useFilter(data, new ReleaseFilter(data, i)));
            
            // updates remaining current iteration state
            i ++;

            // performs evaluation and returns it
            classifier.buildClassifier(train);
            eval = new RichEvaluation(test);
            eval.evaluateModel(classifier, test);
            eval.setNumOfTrainingReleases(new ProjectResourceInstances(train).getNumOfReleases());
            eval.setTrainingPercentage((train.size() * 100.0) / data.size());
            eval.setDefectiveInTrainingPercentage((new ProjectResourceInstances(train).getNumOfDefective() * 100.0) / train.size());
            eval.setDefectiveInTestingPercentage((new ProjectResourceInstances(test).getNumOfDefective() * 100.0) / test.size());
            
        } catch (Exception e) {
            throw new UnableToGenerateNextEvaluationException(e);
        }

        return eval;
    }
}
