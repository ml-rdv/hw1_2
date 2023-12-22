package PracticeClassesInterfacesPackagesModules.MyLinkedListIsGeneric;

public class Node<E> {
    private final E data;
    private Node<E> previous;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
    }

    public Node(E data, Node<E> previous, Node<E> next) {
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
