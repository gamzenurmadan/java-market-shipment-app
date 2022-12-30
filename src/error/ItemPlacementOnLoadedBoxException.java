package error;

public class ItemPlacementOnLoadedBoxException extends RuleException {

    public ItemPlacementOnLoadedBoxException(String itemSerialNumber, String boxSerialNumber) {
        super("Item " + itemSerialNumber + " cannot be placed to box " + boxSerialNumber + "(EX: " + 
                ExceptionEnum.ITEM_PLACEMENT_ON_LOADED_BOX_EXCEPTION.getValue() + " item placement on loaded box)");
    }
}
