package it.torkin;

import java.util.Iterator;
import java.util.NoSuchElementException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.filters.Filter;

/**
 * WalkForward implementation algorithm.
 * Such classifier evaluation algorithm can be imagined as a generator of evaluations.
 * It can be used to walk through all evaluations calling its next method.
 */
public class WalkForward implements Iterator<Evaluation>{
    
    // cross-iteration state

    /**classifier to evaluate */
    private Class<? extends Classifier> classifierClass;
    
    /**time ordered dataset */
    private Instances data;
    
    // current iteration state
    private Instances train;
    private Instances test;
    private int i;

    public WalkForward(Class<? extends Classifier> classifierClass, Instances dataset){
        this.classifierClass = classifierClass;
        this.data = dataset;
        this.train = new Instances(data, data.size());
        this.test = new Instances(data, 1);
        this.i = 1;
    }

    @Override
    public boolean hasNext() {
        return i != data.size() - 1;
    }
    
    @Override
    public Evaluation next() {
        
        Evaluation eval;
        Classifier classifier;
        
        if (i >= data.size()){
            throw new NoSuchElementException();
        }
        
        try {
            
            // updates training set with (i-i)-release entries
            train.addAll(Filter.useFilter(data, new ReleaseFilter(data, i - 1)));

            // takes i-release as the testing set
            test.clear();
            test.addAll(Filter.useFilter(data, new ReleaseFilter(data, i)));
            
            // updates remaining current iteration state
            classifier = classifierClass.getConstructor().newInstance();
            i ++;

            // performs evaluation and returns it
            classifier.buildClassifier(train);
            eval = new Evaluation(test);
            eval.evaluateModel(classifier, test);

        } catch (Exception e) {
            throw new UnableToGenerateNextEvaluationException(e);
        }

        return eval;
    }
}
