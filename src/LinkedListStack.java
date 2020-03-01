public class LinkedListStack {
    private Node head;  // the head node

    // private nested class for Node
    private class Node {
        String data;
        Node next;
    }

    public LinkedListStack() { // internal data structure to keep track of head
        head = null;
    }

    // Pop node from the beginning of the stack
    public String pop() throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException();
        }
        String data = head.data; // we need to do this because pop returns data
        head = head.next; // set head node to head.next

        return data;
    }

    public int size() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }

    // Add data to the beginning of the list for demonstrating behaviour of stack
    public void push(String data) {
        Node OGHead = head; // we need this because new pushed node needs to point to the OGHead (original gangsta head)
        head = new Node();
        head.data = data;
        head.next = OGHead;
    }

    public StringBuilder allData() {
        // iterate through the list, get data and add it to string with new line in between
        StringBuilder sequence = new StringBuilder();
        String separator = "";
        Node temp = this.head;
        while (temp != null) {
            String instruction = temp.data;
            sequence.append(separator).append(instruction);
            separator = "\n";
            temp = temp.next; // this will iterate through the list and print out the data in each node
        }
        return sequence;
    }

    public void reverse() {
        // reverse the linked list. Needed to display all data with the top being the bottom item on the stack.
        Node current = this.head;
        Node prev = null;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        this.head = prev;
    }
}

/**
 *
 * Exception to indicate that LinkedList is empty. Occurs when popping from an empty list.
 */
class LinkedListEmptyException extends RuntimeException {
    public LinkedListEmptyException() {
        // used in this class when popping a stack with no items.
        super();
    }

    public LinkedListEmptyException(String msg) {
        // used in the parent class when popping a stack with no items to add a custom message.
        super(msg);
    }
}

