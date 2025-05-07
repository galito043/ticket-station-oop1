package Exceptions;

public class EmptyPurchaseParametersException extends RuntimeException {
    public EmptyPurchaseParametersException(String message) {
        super(message);
    }
}
