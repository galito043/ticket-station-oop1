package Exceptions;

public class MissingCheckParameterException extends RuntimeException {
  public MissingCheckParameterException(String message) {
    super(message);
  }
}
