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
            addHead(number);
        } else {
            MyBidirectionalLinkedList tmp = head.clone();
            MyBidirectionalLinkedList newElement = new MyBidirectionalLinkedList();
            newElement.data = number;
            newElement.next = tmp;
            tmp.previous = newElement;
            head = newElement;
        }
    }

    private void addHead(int number) {
        head = new MyBidirectionalLinkedList();
        head.data = number;
    }

    public void InsertAtEnd(int number){
        if (isEmpty()) {
            addHead(number);
        }
        MyBidirectionalLinkedList currentValue = head;
        MyBidirectionalLinkedList newElement = new MyBidirectionalLinkedList();
        newElement.data = number;
        while (true) {
            if (currentValue.next == null) {
                currentValue.next = newElement;
                newElement.previous = currentValue;
                return;
            }
            currentValue = currentValue.next;
        }
    }

    public void deleteAtHead() {
        if (!isEmpty()) {
            head = head.next;
            head.previous = null;
        }
    }

    public void delete(int number) {
        if (isEmpty()) {
            return;
        }
        MyBidirectionalLinkedList currentValue = head;
        boolean isHead = false;
        MyBidirectionalLinkedList previousValue = null;
        MyBidirectionalLinkedList nextValue = null;
        while (true) {
            if (currentValue == null) {
                return;
            }
            if (currentValue.data == number) {
                if (currentValue == head) {
                    head = null;
                    isHead = true;
                }
                if (currentValue.next != null) {
                    nextValue = currentValue.next;
                    nextValue.previous = null;
                }
                if (currentValue.previous != null) {
                    previousValue = currentValue.previous;
                    previousValue.next = null;
                }
                if (nextValue != null) {
                    nextValue.previous = previousValue;
                    if (isHead) {
                        head = nextValue;
                    }
                }
                if (previousValue != null) {
                    currentValue.previous.next = nextValue;
                }
                return;
            }
            currentValue = currentValue.next;
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