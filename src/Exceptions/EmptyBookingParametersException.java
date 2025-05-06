package Exceptions;

public class EmptyBookingParametersException extends RuntimeException {
    public EmptyBookingParametersException(String message) {
        super(message);
    }
}
