package it.torkin.entity;

import java.util.NoSuchElementException;

public enum FeatureSelectionApproach {

    NONE("none"),
    FILTER("filter"),
    WRAPPER("wrapper")
    ;

    private String name;
        
    private FeatureSelectionApproach(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        
        return this.name;
    }

    public static FeatureSelectionApproach getFeatureSelectionApproach(String name){
        for (FeatureSelectionApproach approach : FeatureSelectionApproach.values()){
            if (approach.toString().compareTo(name) == 0){
                return approach;
            }
        }
        throw new NoSuchElementException(String.format("Unimplemented feature selection approach: %s", name));
    }

}
