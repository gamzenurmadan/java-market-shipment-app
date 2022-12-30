package error;

public class ContainerCapacityException extends RuleException {

    public ContainerCapacityException() {
        super();
    }

    public ContainerCapacityException(String containerSerialNumber) {
        super("Cannot load to container" + containerSerialNumber + "(EX: "
                + ExceptionEnum.CONTAINER_CAPACITY_EXCEPTION.getValue() + " container capacity reached)");
    }
}
