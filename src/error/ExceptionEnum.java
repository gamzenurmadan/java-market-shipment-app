package error;
public enum ExceptionEnum {
    BOX_OR_CONTAINER_SHIPPED_EXCEPTION(1),
    CONTAINER_CAPACITY_EXCEPTION(2),
    INCOMPATIBLE_TYPE_FOR_MASS_BOX_EXCEPTION(3),
    INCOMPATIBLE_TYPE_FOR_NUMBER_BOX_EXCEPTION(4),
    ITEM_ALREADY_BOXED_EXCEPTION(5),
    ITEM_PLACEMENT_ON_CONTAINER_EXCEPTION(6),
    ITEM_PLACEMENT_ON_LOADED_BOX_EXCEPTION(7),
    MASS_BOX_CAPACITY_EXCEPTION(8),
    NUMBER_BOX_CAPACITY_EXCEPTION(9),
    SAME_SERIAL_NUMBER_PRODUCTION_EXCEPTION(10),
    BOX_VOLUME_EXCEEDED_EXCEPTION(11),
    BOX_ALREADY_IN_CONTAINER_EXCEPTION(12);

    private int value;

    ExceptionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
