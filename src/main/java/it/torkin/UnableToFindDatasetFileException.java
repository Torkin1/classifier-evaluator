package it.torkin;

public class UnableToFindDatasetFileException extends Exception{

    public UnableToFindDatasetFileException(String path) {
        super(String.format("unable to find dataset file %s", path));
    }

}
