package error;

public class MassBoxCapacityException extends RuleException {

    public MassBoxCapacityException(String massBoxSerialnumber) {
        super("Item cannot be loaded to mass box " + massBoxSerialnumber + "(EX: "
                + ExceptionEnum.MASS_BOX_CAPACITY_EXCEPTION.getValue() + " max mass reached)");
    }
}
