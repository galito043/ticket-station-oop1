package Exceptions;

public class EmptyFreeSeatsCommandException extends RuntimeException {
    public EmptyFreeSeatsCommandException(String message) {
        super(message);
    }
}
