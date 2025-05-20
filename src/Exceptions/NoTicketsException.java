package Exceptions;

public class NoTicketsException extends RuntimeException {
    public NoTicketsException(String message) {
        super(message);
    }
}
