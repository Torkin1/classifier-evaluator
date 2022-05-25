package it.torkin;

public class UnableToPrepareClassifiersException extends Exception{

    public UnableToPrepareClassifiersException(String name, Exception e) {
        super(String.format("Unable to prepare classifier %s", name), e);
    }

}
