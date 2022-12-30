package error;

public class ItemAlreadyBoxedException extends RuleException {

    public ItemAlreadyBoxedException(String itemSerialNumber) {
        super("Item " + itemSerialNumber + " cannot be boxed" + "(EX: "
                + ExceptionEnum.ITEM_ALREADY_BOXED_EXCEPTION.getValue() + " item already boxed)");
    }
}
