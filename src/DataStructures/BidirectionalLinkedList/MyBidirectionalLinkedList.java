package DataStructures.BidirectionalLinkedList;

public class MyBidirectionalLinkedList {

    private Node head;
    private int size = 0; // Для чего нужна переменная size?

    public void insertAtHead(int number) throws CloneNotSupportedException {
        if (isEmpty()) {
            addHead(number);
        } else {
            Node tmp = head.cloneNode();
            Node newElement = new Node();
            newElement.setData(number);
            newElement.setNext(tmp);
            tmp.setPrevious(newElement);
            head = newElement;
        }
        size++;
    }

    private void addHead(int number) {
        head = new Node();
        head.setData(number);
    }

    public void insertAtEnd(int number) {
        if (isEmpty()) {
            addHead(number);
        }
        Node currentValue = head;
        Node newElement = new Node();
        newElement.setData(number);
        while (true) {
            if (currentValue.getNext() == null) {
                currentValue.setNext(newElement);
                newElement.setPrevious(currentValue);
                size++;
                return;
            }
            currentValue = currentValue.getNext();
        }

    }

    public void deleteAtHead() {
        if (!isEmpty()) {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        }
    }

    public void delete(int number) {
        if (isEmpty()) {
            return;
        }
        Node currentValue = head;
        boolean isHead = false;
        Node previousValue = null;
        Node nextValue = null;
        while (true) {
            if (currentValue == null) {
                return;
            }
            if (currentValue.getData() == number) {
                if (currentValue == head) {
                    head = null;
                    isHead = true;
                }
                if (currentValue.getNext() != null) {
                    nextValue = currentValue.getNext();
                    nextValue.setPrevious(null);
                }
                if (currentValue.getPrevious() != null) {
                    previousValue = currentValue.getPrevious();
                    previousValue.setNext(null);
                }
                if (nextValue != null) {
                    nextValue.setPrevious(previousValue);
                    if (isHead) {
                        head = nextValue;
                    }
                }
                if (previousValue != null) {
                    currentValue.getPrevious().setNext(nextValue);
                }
                size--;
                return;
            }
            currentValue = currentValue.getNext();
        }

    }

    public int search(int number) {
        if (isEmpty()) {
            return -1;
        }
        Node returnValue = head;
        while (returnValue != null && returnValue.getData() != number) {
            returnValue = returnValue.getNext();
        }
        return returnValue == null ? 0 : returnValue.getData();
    }

    private boolean isEmpty() {
//        return head == null;
        return size == 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        Node currentValue = head;
        StringBuilder b = new StringBuilder();
        b.append('[');
        while (true) {
            b.append(currentValue.getData());
            currentValue = currentValue.getNext();
            if (currentValue == null) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }

}