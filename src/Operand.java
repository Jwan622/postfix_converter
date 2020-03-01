public class Operand {
    public static boolean isOperand(char character) {
        return Character.isLetter(character) || Character.isDigit(character);
    }
}

/**
 *
 * Exception to indicate that Operand is invalid.
 */
class OperandException extends RuntimeException {
    public OperandException(String msg){
        super(msg);
    }
}