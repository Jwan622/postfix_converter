@SuppressWarnings("FieldCanBeLocal")

public class Operator {
    private String operator;

    private String ADD = "+";
    private String SUBTRACT = "-";
    private String DIVISION = "/";
    private String MULTIPLY = "*";
    private String EXPONENT = "^";

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

    public int precedence() {
        if (operator.equals(ADD)) return 1;
        if (operator.equals(SUBTRACT)) return 1;
        if (operator.equals(DIVISION)) return 2;
        if (operator.equals(MULTIPLY)) return 2;
        if (operator.equals(EXPONENT)) return 3;
        return -1;
    }

    public int hasPrecedence(Operator op2) {
        if (this.precedence() > op2.precedence()) return 1;
        if (this.precedence() < op2.precedence()) return -1;
        return 0;
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
