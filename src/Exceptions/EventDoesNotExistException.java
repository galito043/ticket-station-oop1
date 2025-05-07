package Exceptions;

public class EventDoesNotExistException extends RuntimeException {
    public EventDoesNotExistException(String message) {
        super(message);
    }
}
