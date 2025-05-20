package Exceptions;

public class NoEventsToShowException extends RuntimeException {
    public NoEventsToShowException(String message) {
        super(message);
    }
}
