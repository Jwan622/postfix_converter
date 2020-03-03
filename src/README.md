__Instructions__

Runs on:
- Java 12
- IntelliJ IDEA


- to run the application, run these command inside the `src` folder:
```
javac PostfixConverter.java LinkedListStack.java Operator.java Operand.java Cpu.java
```

and then

`java PostfixConverter postfixInput.txt convertedPostfix.txt Cpu.java Operand.java Operator.java LinkedListStack.java`

That will ingest the postfixInput.txt test cases and output the machine instructions to `convertedPostfix.txt`.

To generate javadocs while in the `src` folder:

`javadoc -d doc *.java` and view them using `open doc/index.html`