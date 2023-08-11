package DataStructures.BidirectionalLinkedList;

public class MyBidirectionalLinkedList implements Cloneable {

    private int data;
    private MyBidirectionalLinkedList head;
    private MyBidirectionalLinkedList previous;
    private MyBidirectionalLinkedList next;

    public MyBidirectionalLinkedList clone() throws CloneNotSupportedException {

        return (MyBidirectionalLinkedList) super.clone();
    }

    public void insertAtHead(int number) throws CloneNotSupportedException {
        if (isEmpty()) {
            head = new MyBidirectionalLinkedList();
            head.data = number;
        } else {
            MyBidirectionalLinkedList tmp = head.clone();
            head.data = number;
            head.next = tmp;
            tmp.previous = head;
        }
    }

    public void deleteAtHead() {
        if (!isEmpty()) {
            head = head.next;
            head.previous = null;
        }
    }

    public int search(int number) {
        if (isEmpty()) {
            return -1;
        }
        MyBidirectionalLinkedList returnValue = head;
        while (returnValue != null && returnValue.data != number) {
            returnValue = returnValue.next;
        }
        return returnValue == null ? 0 : returnValue.data;
    }

    private boolean isEmpty() {
        return head == null;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        MyBidirectionalLinkedList currentValue = head;
        StringBuilder b = new StringBuilder();
        b.append('[');
        while (true) {
            b.append(currentValue.data);
            currentValue = currentValue.next;
            if (currentValue == null) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }
}