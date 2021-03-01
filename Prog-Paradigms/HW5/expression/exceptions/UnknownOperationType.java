package expression.exceptions;

public class UnknownOperationType extends IllegalStateException {
    public UnknownOperationType(String mode){
        super("Unknown operations type: " + mode);
    }
}
