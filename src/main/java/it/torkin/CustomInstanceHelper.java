package it.torkin;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class CustomInstanceHelper implements Instance{

    private Instance decorated;
    public Object copy() {
        return decorated.copy();
    }

    public Attribute attribute(int index) {
        return decorated.attribute(index);
    }

    public Attribute attributeSparse(int indexOfIndex) {
        return decorated.attributeSparse(indexOfIndex);
    }

    public Attribute classAttribute() {
        return decorated.classAttribute();
    }

    public int classIndex() {
        return decorated.classIndex();
    }

    public boolean classIsMissing() {
        return decorated.classIsMissing();
    }

    public double classValue() {
        return decorated.classValue();
    }

    public Instance copy(double[] values) {
        return decorated.copy(values);
    }

    public Instances dataset() {
        return decorated.dataset();
    }

    public void deleteAttributeAt(int position) {
        decorated.deleteAttributeAt(position);
    }

    public Enumeration<Attribute> enumerateAttributes() {
        return decorated.enumerateAttributes();
    }

    public boolean equalHeaders(Instance inst) {
        return decorated.equalHeaders(inst);
    }

    public String equalHeadersMsg(Instance inst) {
        return decorated.equalHeadersMsg(inst);
    }

    public boolean hasMissingValue() {
        return decorated.hasMissingValue();
    }

    public int index(int position) {
        return decorated.index(position);
    }

    public void insertAttributeAt(int position) {
        decorated.insertAttributeAt(position);
    }

    public boolean isMissing(int attIndex) {
        return decorated.isMissing(attIndex);
    }

    public boolean isMissingSparse(int indexOfIndex) {
        return decorated.isMissingSparse(indexOfIndex);
    }

    public boolean isMissing(Attribute att) {
        return decorated.isMissing(att);
    }

    public Instance mergeInstance(Instance inst) {
        return decorated.mergeInstance(inst);
    }

    public int numAttributes() {
        return decorated.numAttributes();
    }

    public int numClasses() {
        return decorated.numClasses();
    }

    public int numValues() {
        return decorated.numValues();
    }

    public void replaceMissingValues(double[] array) {
        decorated.replaceMissingValues(array);
    }

    public void setClassMissing() {
        decorated.setClassMissing();
    }

    public void setClassValue(double value) {
        decorated.setClassValue(value);
    }

    public void setClassValue(String value) {
        decorated.setClassValue(value);
    }

    public void setDataset(Instances instances) {
        decorated.setDataset(instances);
    }

    public void setMissing(int attIndex) {
        decorated.setMissing(attIndex);
    }

    public void setMissing(Attribute att) {
        decorated.setMissing(att);
    }

    public void setValue(int attIndex, double value) {
        decorated.setValue(attIndex, value);
    }

    public void setValueSparse(int indexOfIndex, double value) {
        decorated.setValueSparse(indexOfIndex, value);
    }

    public void setValue(int attIndex, String value) {
        decorated.setValue(attIndex, value);
    }

    public void setValue(Attribute att, double value) {
        decorated.setValue(att, value);
    }

    public void setValue(Attribute att, String value) {
        decorated.setValue(att, value);
    }

    public void setWeight(double weight) {
        decorated.setWeight(weight);
    }

    public Instances relationalValue(int attIndex) {
        return decorated.relationalValue(attIndex);
    }

    public Instances relationalValue(Attribute att) {
        return decorated.relationalValue(att);
    }

    public String stringValue(int attIndex) {
        return decorated.stringValue(attIndex);
    }

    public String stringValue(Attribute att) {
        return decorated.stringValue(att);
    }

    public double[] toDoubleArray() {
        return decorated.toDoubleArray();
    }

    public String toStringNoWeight(int afterDecimalPoint) {
        return decorated.toStringNoWeight(afterDecimalPoint);
    }

    public String toStringNoWeight() {
        return decorated.toStringNoWeight();
    }

    public String toStringMaxDecimalDigits(int afterDecimalPoint) {
        return decorated.toStringMaxDecimalDigits(afterDecimalPoint);
    }

    public String toString(int attIndex, int afterDecimalPoint) {
        return decorated.toString(attIndex, afterDecimalPoint);
    }

    public String toString(int attIndex) {
        return decorated.toString(attIndex);
    }

    public String toString(Attribute att, int afterDecimalPoint) {
        return decorated.toString(att, afterDecimalPoint);
    }

    public String toString(Attribute att) {
        return decorated.toString(att);
    }

    public double value(int attIndex) {
        return decorated.value(attIndex);
    }

    public double valueSparse(int indexOfIndex) {
        return decorated.valueSparse(indexOfIndex);
    }

    public double value(Attribute att) {
        return decorated.value(att);
    }

    public double weight() {
        return decorated.weight();
    }

    public CustomInstanceHelper(Instance instance) {
        this.decorated = instance;
    }
    
    public Attribute getAttribute(String name){
        Enumeration<Attribute> attributes = decorated.enumerateAttributes();
        Attribute a;
        while (attributes.hasMoreElements()){
            a = attributes.nextElement();
            if (a.name().compareTo(name) == 0){
                return a;
            }
        }
        throw new NoSuchElementException(name);
    }

    public Attribute getReleaseAttribute(){
        return this.getAttribute("Release");
    }
    
}
