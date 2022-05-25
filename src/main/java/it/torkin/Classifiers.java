package it.torkin;

import java.util.EnumSet;
import java.util.NoSuchElementException;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.RandomForest;

public enum Classifiers {

    NAIVE_BAYES("naiveBayes", NaiveBayes.class),
    RANDOM_FOREST("randomForest", RandomForest.class),
    IBK("ibk", IBk.class)
    ;

    private final String name;
    private final Class<? extends Classifier> implementation;

    private Classifiers(String name, Class<? extends Classifier> implementation){
        this.name = name;
        this.implementation = implementation;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Classifier> getImplementation() {
        return implementation;
    }

    public static Classifiers getClassifier(String name) {
        for (Classifiers c : EnumSet.allOf(Classifiers.class)) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new NoSuchElementException(String.format("no classifier implementation registered with name %s", name));
    }
}
