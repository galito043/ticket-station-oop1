package Exceptions;

public class NoFileOpenException extends RuntimeException {
    public NoFileOpenException(String message) {
        super(message);
    }
}
