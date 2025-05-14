package Exceptions;

public class TooManyParametersException extends RuntimeException {
    public TooManyParametersException(String message) {
        super(message);
    }
}
