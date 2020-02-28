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

    public void handleOperator(Operator operator) {
        String charA = this.llsForPostfix.pop();
        String charB = this.llsForPostfix.pop();
        buildInstruction(charA, charB, operator);
        handlePostfix(charA, charB, operator);
    }

    public void handleOperand(String operand) {
        this.llsForPostfix.push(operand);
    }

    private void buildInstruction(String charA, String charB, Operator operator) {
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
        } else {
            llsForOutput.push("LD " + charB);
            llsForOutput.push("DV " + charA);
            llsForOutput.push("ST " + tempVariable);
            this.callCount++;
        }
    }

    private void handlePostfix(String charA, String charB, Operator operator) {
        this.llsForPostfix.push(generateTempName());
    }

    public StringBuilder instructionSequence() {
        llsForOutput.reverse();
        return llsForOutput.allData();
    }

    public String generateTempName() {
        return "TEMP" + Integer.toString(this.callCount);
    }
}
