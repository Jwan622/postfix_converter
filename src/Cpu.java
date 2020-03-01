public class Cpu {
    private LinkedListStack llsForPostfix;
    private LinkedListStack llsForOutput;
    private int callCount = 1;

    Cpu() {
        // this linkedListStack is for processing the postfix
        this.llsForPostfix = new LinkedListStack();
        // the instruction output can also be stored on a stack until written to file. So we'll be using two stacks.
        this.llsForOutput = new LinkedListStack();
    }

    public void handleOperator(Operator operator) throws LinkedListEmptyException {
        try {
            String charA = this.llsForPostfix.pop();
            String charB = this.llsForPostfix.pop();
            handlePostfix();
            buildInstruction(charA, charB, operator);
        } catch (LinkedListEmptyException exception) {
            throw new LinkedListEmptyException("Stack is empty, cannot execute operation: " + operator.token());
        }
    }

    public void handleOperand(String operand) {
        this.llsForPostfix.push(operand);
    }

    private void buildInstruction(String charA, String charB, Operator operator) throws OperatorException {
        String tempVariable = generateTempName();

        if (operator.isAdd()) {
            llsForOutput.push("LD " + charB);
            llsForOutput.push("AD " + charA);
            llsForOutput.push("ST " + tempVariable);
            this.callCount++;
        } else if (operator.isMultiply()) {
            llsForOutput.push("LD " + charB);
            llsForOutput.push("ML " + charA);
            llsForOutput.push("ST " + tempVariable);
            this.callCount++;
        } else if (operator.isSubtract()) {
            llsForOutput.push("LD " + charB);
            llsForOutput.push("SB " + charA);
            llsForOutput.push("ST " + tempVariable);
            this.callCount++;
        } else if (operator.isDivision()){
            llsForOutput.push("LD " + charB);
            llsForOutput.push("DV " + charA);
            llsForOutput.push("ST " + tempVariable);
            this.callCount++;
        } else {
            throw new OperatorException("operator: " + operator.token() + " not supported");
        }
    }

    private void handlePostfix() {
        this.llsForPostfix.push(generateTempName());
    }

    public StringBuilder instructionSequence() {
        llsForOutput.reverse();
        StringBuilder data = llsForOutput.allData();
        // to keep this method idempotent, reverse it back to the original state so this method can be called multiple
        // times and return the same result.
        llsForOutput.reverse();
        return data;
    }

    public String generateTempName() {
        return "TEMP" + Integer.toString(this.callCount);
    }

    public boolean postfixNeedsMoreOperators() {
        // if the postfix has > 1 left at the end, that means the postfix statement needs more variables.
        return this.llsForPostfix.size() > 1;
    }
}
