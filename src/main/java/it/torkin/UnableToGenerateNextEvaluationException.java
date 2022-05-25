package it.torkin;

public class UnableToGenerateNextEvaluationException extends RuntimeException{

    public UnableToGenerateNextEvaluationException(Exception e) {
        super(e);
    }

}
