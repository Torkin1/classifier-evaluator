package it.torkin.entity;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instance;
import weka.core.Instances;

/**describes a buggyness datatset */
public class ProjectResourceInstances extends AbstractList<Instance>{

    private Instances decorated;
    
    public ProjectResourceInstances(Instances decorated){
        this.decorated = decorated;
    }

    @Override
    public boolean add(Instance instance) {
        return decorated.add(instance);
    }

    @Override
    public void add(int index, Instance instance) {
        decorated.add(index, instance);
    }

    @Override
    public boolean addAll(int arg0, Collection<? extends Instance> arg1) {
        return decorated.addAll(arg0, arg1);
    }

    @Override
    public boolean addAll(Collection<? extends Instance> arg0) {
        return decorated.addAll(arg0);
    }

    @Override
    public void clear() {
        decorated.clear();
    }

    @Override
    public boolean contains(Object o) {
        return decorated.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        return decorated.containsAll(arg0);
    }

    @Override
    public boolean equals(Object arg0) {
        return decorated.equals(arg0);
    }

    @Override
    public void forEach(Consumer<? super Instance> arg0) {
        decorated.forEach(arg0);
    }

    @Override
    public int hashCode() {
        return decorated.hashCode();
    }

    @Override
    public int indexOf(Object o) {
        return decorated.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return decorated.isEmpty();
    }

    @Override
    public Iterator<Instance> iterator() {
        return decorated.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return decorated.lastIndexOf(o);
    }

    @Override
    public ListIterator<Instance> listIterator() {
        return decorated.listIterator();
    }

    @Override
    public ListIterator<Instance> listIterator(int index) {
        return decorated.listIterator(index);
    }

    @Override
    public Stream<Instance> parallelStream() {
        return decorated.parallelStream();
    }

    @Override
    public boolean remove(Object o) {
        return decorated.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return decorated.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super Instance> filter) {
        return decorated.removeIf(filter);
    }

    @Override
    public void replaceAll(UnaryOperator<Instance> operator) {
        decorated.replaceAll(operator);
    }

    public Instances stringFreeStructure() {
        return decorated.stringFreeStructure();
    }

    public boolean allAttributeWeightsIdentical() {
        return decorated.allAttributeWeightsIdentical();
    }

    public boolean allInstanceWeightsIdentical() {
        return decorated.allInstanceWeightsIdentical();
    }

    public Attribute attribute(int index) {
        return decorated.attribute(index);
    }

    public Attribute attribute(String name) {
        return decorated.attribute(name);
    }

    public boolean checkForAttributeType(int attType) {
        return decorated.checkForAttributeType(attType);
    }

    public boolean checkForStringAttributes() {
        return decorated.checkForStringAttributes();
    }

    public boolean checkInstance(Instance instance) {
        return decorated.checkInstance(instance);
    }

    public Attribute classAttribute() {
        return decorated.classAttribute();
    }

    public int classIndex() {
        return decorated.classIndex();
    }

    public void compactify() {
        decorated.compactify();
    }

    public void delete() {
        decorated.delete();
    }

    public void delete(int index) {
        decorated.delete(index);
    }

    public void deleteAttributeAt(int position) {
        decorated.deleteAttributeAt(position);
    }

    public void deleteAttributeType(int attType) {
        decorated.deleteAttributeType(attType);
    }

    public void deleteStringAttributes() {
        decorated.deleteStringAttributes();
    }

    public void deleteWithMissing(int attIndex) {
        decorated.deleteWithMissing(attIndex);
    }

    public void deleteWithMissing(Attribute att) {
        decorated.deleteWithMissing(att);
    }

    public void deleteWithMissingClass() {
        decorated.deleteWithMissingClass();
    }

    public Enumeration<Attribute> enumerateAttributes() {
        return decorated.enumerateAttributes();
    }

    public Enumeration<Instance> enumerateInstances() {
        return decorated.enumerateInstances();
    }

    public String equalHeadersMsg(Instances dataset) {
        return decorated.equalHeadersMsg(dataset);
    }

    public boolean equalHeaders(Instances dataset) {
        return decorated.equalHeaders(dataset);
    }

    public Instance firstInstance() {
        return decorated.firstInstance();
    }

    public Random getRandomNumberGenerator(long seed) {
        return decorated.getRandomNumberGenerator(seed);
    }

    public void insertAttributeAt(Attribute att, int position) {
        decorated.insertAttributeAt(att, position);
    }

    public Instance instance(int index) {
        return decorated.instance(index);
    }

    public double kthSmallestValue(Attribute att, int k) {
        return decorated.kthSmallestValue(att, k);
    }

    public double kthSmallestValue(int attIndex, int k) {
        return decorated.kthSmallestValue(attIndex, k);
    }

    public Instance lastInstance() {
        return decorated.lastInstance();
    }

    public double meanOrMode(int attIndex) {
        return decorated.meanOrMode(attIndex);
    }

    public double meanOrMode(Attribute att) {
        return decorated.meanOrMode(att);
    }

    public int numAttributes() {
        return decorated.numAttributes();
    }

    public int numClasses() {
        return decorated.numClasses();
    }

    public int numDistinctValues(int attIndex) {
        return decorated.numDistinctValues(attIndex);
    }

    public int numDistinctValues(Attribute att) {
        return decorated.numDistinctValues(att);
    }

    public int numInstances() {
        return decorated.numInstances();
    }

    public void randomize(Random random) {
        decorated.randomize(random);
    }

    public void replaceAttributeAt(Attribute att, int position) {
        decorated.replaceAttributeAt(att, position);
    }

    public String relationName() {
        return decorated.relationName();
    }

    @Override
    public Instance remove(int index) {
        return decorated.remove(index);
    }

    public void renameAttribute(int att, String name) {
        decorated.renameAttribute(att, name);
    }

    public void setAttributeWeight(Attribute att, double weight) {
        decorated.setAttributeWeight(att, weight);
    }

    public void setAttributeWeight(int att, double weight) {
        decorated.setAttributeWeight(att, weight);
    }

    public void renameAttribute(Attribute att, String name) {
        decorated.renameAttribute(att, name);
    }

    public void renameAttributeValue(int att, int val, String name) {
        decorated.renameAttributeValue(att, val, name);
    }

    public void renameAttributeValue(Attribute att, String val, String name) {
        decorated.renameAttributeValue(att, val, name);
    }

    public Instances resample(Random random) {
        return decorated.resample(random);
    }

    public Instances resampleWithWeights(Random random) {
        return decorated.resampleWithWeights(random);
    }

    public Instances resampleWithWeights(Random random, boolean[] sampled) {
        return decorated.resampleWithWeights(random, sampled);
    }

    public Instances resampleWithWeights(Random random, boolean representUsingWeights) {
        return decorated.resampleWithWeights(random, representUsingWeights);
    }

    public Instances resampleWithWeights(Random random, boolean[] sampled, boolean representUsingWeights) {
        return decorated.resampleWithWeights(random, sampled, representUsingWeights);
    }

    public Instances resampleWithWeights(Random random, boolean[] sampled, boolean representUsingWeights,
            double sampleSize) {
        return decorated.resampleWithWeights(random, sampled, representUsingWeights, sampleSize);
    }

    public Instances resampleWithWeights(Random random, double[] weights) {
        return decorated.resampleWithWeights(random, weights);
    }

    public Instances resampleWithWeights(Random random, double[] weights, boolean[] sampled) {
        return decorated.resampleWithWeights(random, weights, sampled);
    }

    public Instances resampleWithWeights(Random random, double[] weights, boolean[] sampled,
            boolean representUsingWeights) {
        return decorated.resampleWithWeights(random, weights, sampled, representUsingWeights);
    }

    public Instances resampleWithWeights(Random random, double[] weights, boolean[] sampled,
            boolean representUsingWeights, double sampleSize) {
        return decorated.resampleWithWeights(random, weights, sampled, representUsingWeights, sampleSize);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return decorated.retainAll(c);
    }

    @Override
    public Instance set(int index, Instance instance) {
        return decorated.set(index, instance);
    }

    public void setClass(Attribute att) {
        decorated.setClass(att);
    }

    public void setClassIndex(int classIndex) {
        decorated.setClassIndex(classIndex);
    }

    public void setRelationName(String newName) {
        decorated.setRelationName(newName);
    }

    public void sort(int attIndex) {
        decorated.sort(attIndex);
    }

    public void sort(Attribute att) {
        decorated.sort(att);
    }

    @Override
    public void sort(Comparator<? super Instance> arg0) {
        decorated.sort(arg0);
    }

    @Override
    public Spliterator<Instance> spliterator() {
        return decorated.spliterator();
    }

    public void stableSort(int attIndex) {
        decorated.stableSort(attIndex);
    }

    public void stableSort(Attribute att) {
        decorated.stableSort(att);
    }

    public void stratify(int numFolds) {
        decorated.stratify(numFolds);
    }

    @Override
    public Stream<Instance> stream() {
        return decorated.stream();
    }

    @Override
    public List<Instance> subList(int fromIndex, int toIndex) {
        return decorated.subList(fromIndex, toIndex);
    }

    public double sumOfWeights() {
        return decorated.sumOfWeights();
    }

    public Instances testCV(int numFolds, int numFold) {
        return decorated.testCV(numFolds, numFold);
    }

    @Override
    public String toString() {
        return decorated.toString();
    }

    public Instances trainCV(int numFolds, int numFold) {
        return decorated.trainCV(numFolds, numFold);
    }

    public Instances trainCV(int numFolds, int numFold, Random random) {
        return decorated.trainCV(numFolds, numFold, random);
    }

    public double[] variances() {
        return decorated.variances();
    }

    public double variance(int attIndex) {
        return decorated.variance(attIndex);
    }

    public double variance(Attribute att) {
        return decorated.variance(att);
    }

    public AttributeStats attributeStats(int index) {
        return decorated.attributeStats(index);
    }

    public double[] attributeToDoubleArray(int index) {
        return decorated.attributeToDoubleArray(index);
    }

    public String toSummaryString() {
        return decorated.toSummaryString();
    }

    public void swap(int i, int j) {
        decorated.swap(i, j);
    }

    public String getRevision() {
        return decorated.getRevision();
    }

    @Override
    public Object[] toArray() {
        return decorated.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return decorated.toArray(arg0);
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return decorated.toArray(generator);
    }

    @Override
    public Instance get(int arg0) {
        return decorated.get(0);
    }

    @Override
    public int size() {
        return decorated.size();
    }

    public int getNumOfDefective(){
        int buggynessAttributeIndex;
        double buggynessValueIndex;
        String isBuggy;
        int numOfDefective = 0;
        
        // it is assumed that buggyness attribute is always at the last column of dataset
        for (Instance datum : decorated){
            buggynessAttributeIndex = Buggyness.getFeatureIndex(datum.numAttributes());
            buggynessValueIndex = datum.value(buggynessAttributeIndex);
            isBuggy = datum.attribute(buggynessAttributeIndex).value((int) buggynessValueIndex);
            if (isBuggy.compareTo(Buggyness.YES.getNominalValue()) == 0){
                numOfDefective ++;
            }
        }
        return numOfDefective;
    }

    public int getNumOfReleases(){
        String name;
        Set<String> seen = new HashSet<>();
        for (Instance datum : decorated) {
            name = datum.stringValue(new ProjectResourceInstance(datum).getReleaseAttribute());
            seen.add(name);
        }
        return seen.size();

    }

    
    
}
