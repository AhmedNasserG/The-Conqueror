package exceptions;

public class TargetNotReachedException extends ArmyException {

    public TargetNotReachedException() {}

    public TargetNotReachedException(String s) {
        super(s);
    }
}
