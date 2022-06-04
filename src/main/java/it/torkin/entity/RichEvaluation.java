package it.torkin.entity;

import java.util.Set;

import weka.classifiers.Evaluation;
import weka.core.Instances;

/** Enriched evaluation with more informations about classifier perfomances*/
public class RichEvaluation extends Evaluation{

    public RichEvaluation(Instances data) throws Exception {
        super(data);
    }
    /**dataset key */
    private String datasetName;
    /** num of releases present in training set in current iteration*/
    private int numOfTrainingReleases;
    /**percentage of files in training set */
    private double trainingPercentage;
    /** percentage of defective classes in training set */
    private double defectiveInTrainingPercentage;
    /** percentage of sedective classes in testing set */
    private double defectiveInTestingPercentage;
    /**what classifier has been evaluated */
    private ClassifierImplementation classifier;
    /**wheter or not classes in datatset are present with same number of representing individuals */
    private boolean isDatasetStratified;
    /**What kind of feature selection has been used*/
    private Set<FeatureSelectionApproach> featureSelectionApproaches;
    /** feature selection search sense */
    private boolean isFeatureSearchBackward;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    public String getDatasetName() {
        return datasetName;
    }
    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }
    public int getNumOfTrainingReleases() {
        return numOfTrainingReleases;
    }
    public void setNumOfTrainingReleases(int trainingReleaseNum) {
        this.numOfTrainingReleases = trainingReleaseNum;
    }
    public double getTrainingPercentage() {
        return trainingPercentage;
    }
    public void setTrainingPercentage(double trainingPercentage) {
        this.trainingPercentage = trainingPercentage;
    }
    public double getDefectiveInTrainingPercentage() {
        return defectiveInTrainingPercentage;
    }
    public void setDefectiveInTrainingPercentage(double defectiveInTrainingPercentage) {
        this.defectiveInTrainingPercentage = defectiveInTrainingPercentage;
    }
    public double getDefectiveInTestingPercentage() {
        return defectiveInTestingPercentage;
    }
    public void setDefectiveInTestingPercentage(double defectiveInTestingPercentage) {
        this.defectiveInTestingPercentage = defectiveInTestingPercentage;
    }
    public ClassifierImplementation getClassifier() {
        return classifier;
    }
    public void setClassifier(ClassifierImplementation classifier) {
        this.classifier = classifier;
    }
    public boolean isDatasetStratified() {
        return isDatasetStratified;
    }
    public void setDatasetStratified(boolean isDatasetStratified) {
        this.isDatasetStratified = isDatasetStratified;
    }
    public Set<FeatureSelectionApproach> getFeatureSelectionApproaches() {
        return featureSelectionApproaches;
    }
    public void setFeatureSelectionApproaches(Set<FeatureSelectionApproach> featureSelectionApproaches) {
        this.featureSelectionApproaches = featureSelectionApproaches;
    }
    public boolean isFeatureSearchBackward() {
        return isFeatureSearchBackward;
    }
    public void setFeatureSearchBackward(boolean isFeatureSearchBackward) {
        this.isFeatureSearchBackward = isFeatureSearchBackward;
    }
    
}