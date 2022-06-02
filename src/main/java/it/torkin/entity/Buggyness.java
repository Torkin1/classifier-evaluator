package it.torkin.entity;

/**Describes buggyness labeling class */
public enum Buggyness {

    YES("yes"),
    NO("no"),
    ;

    private static final String LABEL = "isBuggy";
    private final String nominalValue;

    
    private Buggyness(String isBuggy){
        this.nominalValue = isBuggy;
    }


    public String getNominalValue() {
        return nominalValue;
    }


    public static String getLabel() {
        return LABEL;
    }

    public static int getFeatureIndex(int numOfFeatures){
        // we assume that buggyness feature is always the last column of dataset
        return numOfFeatures - 1;
    }
    
}
