package Exceptions;

public class TicketAlreadyBoughtException extends RuntimeException {
    public TicketAlreadyBoughtException(String message) {
        super(message);
    }
}
