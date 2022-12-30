package error;

public class NumberBoxCapacityException extends RuleException {

    public NumberBoxCapacityException(String numberBoxSerialNumber) {
        super("Cannot load more items to number box " + numberBoxSerialNumber
                + " (EX: " + ExceptionEnum.NUMBER_BOX_CAPACITY_EXCEPTION.getValue()
                + " maximum item number capacity reached)");
    }
}
