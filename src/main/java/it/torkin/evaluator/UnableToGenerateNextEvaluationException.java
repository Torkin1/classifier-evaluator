package it.torkin.evaluator;

public class UnableToGenerateNextEvaluationException extends RuntimeException{

    public UnableToGenerateNextEvaluationException(Exception e) {
        super(e);
    }

}
