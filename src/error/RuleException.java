package error;
public class RuleException extends Exception {
    private int errorCode;

    public RuleException() {
        super();
    }

    public RuleException(String message) {
        super(message);
    }

    public RuleException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
