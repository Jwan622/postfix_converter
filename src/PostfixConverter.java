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

    public static void main(String args[]) throws IOException, OperatorException {
        // takes the input filename from args[0] which is passed into this program via the command line
        String inputFile = args[0];
        File inputf = new File(inputFile);

        // this creates an output file which will be properly formatted
        String outputFile = args[1];
        File outputf = new File(outputFile);

        String register;

        //writer to the output file. Using a PrintWriter to take advantage of printf
        PrintWriter outputWriter = new PrintWriter(new FileWriter(outputf));
        BufferedReader br = new BufferedReader(new FileReader(inputf));

        String postfixLine;

        while ((postfixLine = br.readLine()) != null) {
            System.out.println("Handling Postfix line: " + postfixLine);
            outputWriter.println("Handling Postfix line: " + postfixLine);
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
                System.out.println(exception.getMessage() + " " + postfixLine);
                outputWriter.println(exception.getMessage() + " " + postfixLine);
                System.out.println(POSTFIX_DELIMITER);
                outputWriter.println(POSTFIX_DELIMITER);
                continue;
            }
            // if the linkedList that stores the postfix is not empty at the end, then not enough operators
            if (cpu.postfixNeedsMoreOperators()) {
                outputWriter.println("Postfix unbalanced. Too few operators");
                outputWriter.println(POSTFIX_DELIMITER);
                System.out.println("Postfix unbalanced. Too few operators");
                System.out.println(postfixLine + " converted to machine instruction and written to " + outputFile);
                continue;
            }
            outputWriter.println(cpu.instructionSequence());
            outputWriter.println(POSTFIX_DELIMITER);
            System.out.println(cpu.instructionSequence());
            System.out.println(postfixLine + " converted to machine instruction and written to " + outputFile);
            System.out.println(POSTFIX_DELIMITER);
        }

        outputWriter.close();
        br.close();
    };
}