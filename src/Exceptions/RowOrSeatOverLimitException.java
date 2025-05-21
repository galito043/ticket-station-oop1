package Exceptions;

public class RowOrSeatOverLimitException extends RuntimeException {
    public RowOrSeatOverLimitException(String message) {
        super(message);
    }
}
