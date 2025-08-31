package exceptions;

public class InvalidFamilyOperationException extends RuntimeException {
  public InvalidFamilyOperationException(String message) {
    super(message);
  }
}
