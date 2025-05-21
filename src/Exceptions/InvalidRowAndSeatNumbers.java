package Exceptions;

public class InvalidRowAndSeatNumbers extends RuntimeException {
    public InvalidRowAndSeatNumbers(String message) {
        super(message);
    }
}
