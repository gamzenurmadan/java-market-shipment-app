package error;

public class BoxVolumeExceededException extends RuleException {

    public BoxVolumeExceededException() {
        super();
    }

    public BoxVolumeExceededException(String boxSerialNumber) {
        super("Cannot load more items to box " + boxSerialNumber + " (EX: " +
                ExceptionEnum.BOX_VOLUME_EXCEEDED_EXCEPTION.getValue() + " box volume reached)");
    }
}
