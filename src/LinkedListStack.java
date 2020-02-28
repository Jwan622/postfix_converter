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

    // Add data to the beginning of the list for demonstrating behaviour of stack
    public void push(String data) {
        Node OGHead = head; // we need this because new pushed node needs to point to the OGHead (original gangsta head)
        head = new Node();
        head.data = data;
        head.next = OGHead;
    }

    public StringBuilder allData() {
        StringBuilder sequence = new StringBuilder();
        Node temp = this.head;
        while (temp != null) {
            String instruction = temp.data;
            sequence.append(instruction);
            sequence.append("\n");
            temp = temp.next; // this will iterate through the list and print out the data in each node
        }
        return sequence;
    }

    public void reverse() {
        // reverse the linked list
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
        super();
    }
}

