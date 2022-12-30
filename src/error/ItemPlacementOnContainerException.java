package error;

public class ItemPlacementOnContainerException extends RuleException {

    public ItemPlacementOnContainerException(String itemSerialNumber) {
        super("Item " + itemSerialNumber + " cannot be placed to a container" + " (EX: " +
                ExceptionEnum.ITEM_PLACEMENT_ON_CONTAINER_EXCEPTION.getValue() + ")");
    }
}
