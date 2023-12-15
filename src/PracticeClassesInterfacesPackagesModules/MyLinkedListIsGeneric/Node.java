package PracticeClassesInterfacesPackagesModules.MyLinkedListIsGeneric;

import java.util.Date;

public class Node {
    private final Object data;
    private Node previous;
    private Node next;

    public Node(Object data) {
        this.data = data;
    }

    public Node(Object data, Node previous, Node next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getNext() {
        return next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
