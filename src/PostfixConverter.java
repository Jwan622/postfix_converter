/*
Jeffrey Wan
Class EN.605.202.81.SP20 Data Structures
Lab 1
*/

import java.io.*;

/**
 * Example command when running this program: java postfix.PostfixConverter <inputFilename> <outputFilename>
 * @author Jeffrey Wan
 */

public class PostfixConverter {
    private static String POSTFIX_DELIMITER = "--------------";
    private static PrintWriter outputWriter;

    public static void main(String args[]) throws IOException, OperatorException {
        // takes the input filename from args[0] which is passed into this program via the command line
        String inputFile = args[0];
        File inputf = new File(inputFile);

        // this creates an output file which will be properly formatted
        String outputFile = args[1];
        File outputf = new File(outputFile);

        //writer to the output file. Using a PrintWriter to take advantage of printf
        outputWriter = new PrintWriter(new FileWriter(outputf));
        BufferedReader br = new BufferedReader(new FileReader(inputf));

        String postfixLine;

        while ((postfixLine = br.readLine()) != null) {
            // write some gaurd clauses for edge cases
            if (postfixLine.equals("")) {
                // handle empty string case
                writeToFileAndStdOut("Postfix: " + postfixLine + " , is just an empty string. Doing nothing", POSTFIX_DELIMITER);
            } else if (postfixLine.length() == 1 && Operand.isOperand(postfixLine.toCharArray()[0])) {
                // handle single operand edge case
                writeToFileAndStdOut("Postfix " + postfixLine + " , is just a single operand. Doing nothing", POSTFIX_DELIMITER);
            } else {
                // handle common case scenario.
                writeToFileAndStdOut("Handling Postfix line: " + postfixLine);
                Cpu cpu = new Cpu();
                char[] postfixChars = postfixLine.trim().toCharArray(); // remove leading and trailing whitespace and split
                                                                            // into char array
                try {
                    for (char c:postfixChars) {
                        String token = Character.toString(c);
                        if (new Operator(token).isOperator()) {
                            Operator operator = new Operator(token);
                            System.out.println("operator found: " + token);

                            // pop two off the stack if c is an operator. The first popped char is charA
                            cpu.handleOperator(operator);
                        } else {
                            System.out.println("character found: " + token);
                            cpu.handleOperand(token);
                        }
                    }
                } catch (LinkedListEmptyException | OperatorException exception) {
                    writeToFileAndStdOut(exception.getMessage(), POSTFIX_DELIMITER);
                    continue;
                }
                // if the linkedList that stores the postfix has more than 1 item left, then not enough operators
                if (cpu.postfixNeedsMoreOperators()) {
                    writeToFileAndStdOut("Postfix unbalanced. Too few operators");
                    outputWriter.println(POSTFIX_DELIMITER);
                    System.out.println(postfixLine + " converted to machine instruction and written to " + outputFile);
                    continue;
                }
                outputWriter.println(cpu.instructionSequence());
                System.out.println(cpu.instructionSequence());
                outputWriter.println(POSTFIX_DELIMITER);
                System.out.println(postfixLine + " converted to machine instruction and written to " + outputFile);
                System.out.println(POSTFIX_DELIMITER);
            }
        }

        outputWriter.close();
        br.close();
    };

    public static void writeToFileAndStdOut(String first, String second) {
        // writes to stdout and file. This is an overloaded method that can write two different lines to both destinations
        outputWriter.println(first);
        System.out.println(first);
        outputWriter.println(second);
        System.out.println(second);
    }

    public static void writeToFileAndStdOut(String first) {
        // writes to stdout and file
        outputWriter.println(first);
        System.out.println(first);
    }
}