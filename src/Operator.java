@SuppressWarnings("FieldCanBeLocal")

public class Operator {
    private String operator;

    private String ADD = "+";
    private String SUBTRACT = "-";
    private String DIVISION = "/";
    private String MULTIPLY = "*";
    private String EXPONENT = "$";

    Operator(String operator) {
        this.operator = operator;
    }

    public String token() {
        return this.operator;
    }

    public boolean isAdd() {
        return this.operator.equals(ADD);
    }

    public boolean isSubtract() {
        return this.operator.equals(SUBTRACT);
    }

    public boolean isMultiply() {
        return this.operator.equals(MULTIPLY);
    }

    public boolean isDivision() {
        return this.operator.equals(DIVISION);
    }

    public boolean isOperator() {
        if (!this.operator.equals(ADD) &&
                !this.operator.equals(SUBTRACT) &&
                !this.operator.equals(DIVISION) &&
                !this.operator.equals(MULTIPLY) &&
                !this.operator.equals(EXPONENT)
        ) {
            return false;
        } else {
            return true;
        }
    }
}

/**
 *
 * Exception to indicate that LinkedList is empty. Occurs when popping from an empty list.
 */
class OperatorException extends RuntimeException {
    public OperatorException(String msg){
        super(msg);
    }
}