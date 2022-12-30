package error;

public class SameSerialNumberProductionException extends RuleException {

    public SameSerialNumberProductionException(String serialNumber) {
        super(serialNumber, ExceptionEnum.SAME_SERIAL_NUMBER_PRODUCTION_EXCEPTION.getValue());
    }
}