package Exceptions;

public class InvalidTicketCodeException extends RuntimeException {
    public InvalidTicketCodeException(String message) {
        super(message);
    }
}
