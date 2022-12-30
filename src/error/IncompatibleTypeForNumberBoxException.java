package error;

public class IncompatibleTypeForNumberBoxException extends RuleException {

    public IncompatibleTypeForNumberBoxException(String uncountableItemSerialNumber, String numberBoxSerialNumber) {
        super("Cannot place uncountable item " + uncountableItemSerialNumber + " to number box " + numberBoxSerialNumber
                + "(EX: " +
                ExceptionEnum.INCOMPATIBLE_TYPE_FOR_NUMBER_BOX_EXCEPTION.getValue() + ")");
    }
}
