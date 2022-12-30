package error;

public class IncompatibleTypeForMassBoxException extends RuleException {

    public IncompatibleTypeForMassBoxException(String countableItemSerialNumber, String massBoxSerialNumber) {
        super("Cannot place countable item " + countableItemSerialNumber + " in mass box " + massBoxSerialNumber
                + "(EX: " +
                ExceptionEnum.INCOMPATIBLE_TYPE_FOR_MASS_BOX_EXCEPTION.getValue() + ")");
    }
}
