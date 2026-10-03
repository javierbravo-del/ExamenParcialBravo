package utec.examenparcialbravo.exception;

public class AlreadyRequestedException extends RuntimeException {
    public AlreadyRequestedException(String message) {
        super(message);
    }
}
