package error;

public class BoxOrContainerShippedException extends RuleException {

    public BoxOrContainerShippedException(String message) {
        super(message, ExceptionEnum.BOX_OR_CONTAINER_SHIPPED_EXCEPTION.getValue());
    }
}
