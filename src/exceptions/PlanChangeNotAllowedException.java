package exceptions;

public class PlanChangeNotAllowedException extends RuntimeException {
    public PlanChangeNotAllowedException(String message) {
        super(message);
    }
}
