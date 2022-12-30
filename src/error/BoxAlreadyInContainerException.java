package error;

public class BoxAlreadyInContainerException extends RuleException {
    public BoxAlreadyInContainerException(String boxSerialNumber) {
        super("Box " + boxSerialNumber + " cannot be loaded to container" + " (EX: "
                + ExceptionEnum.BOX_ALREADY_IN_CONTAINER_EXCEPTION.getValue() + " box already in container)");
    }
}
