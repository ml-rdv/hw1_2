package PracticeClassesInterfacesPackagesModules.MyLinkedListNodeIsNestedClass;

/**
 * Класс узла для связанного списка переделать на вложенный класс.
 */
public class MyLinkedListNodeIsNestedClass {

    public static class Node {
        private final int data;
        private Node previous;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node previous, Node next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public int getData() {
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

    private Node head;
    private Node tail;
    private int size = 0;

    public void insertAtHead(int number) {
        if (isEmpty()) {
            addHead(number);
        } else {
            Node newElement = new Node(number, null, head);
            head.setPrevious(newElement);
            head = newElement;
            size++;
        }
    }

    private void addHead(int number) {
        head = new Node(number);
        size++;
        tail = head;
    }

    public void insertAtEnd(int number) {
        if (isEmpty()) {
            addHead(number);
        } else {
            Node newElement = new Node(number, tail, null);
            tail.setNext(newElement);
            tail = newElement;
            size++;
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
        if (number == head.getData() && number == tail.getData()) {
            head = null;
            tail = null;
            size--;
        } else if (number == head.getData()) {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        } else if (number == tail.getData()) {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        } else {
            Node currentValue = head;
            while (true) {
                if (currentValue == null) {
                    return;
                }
                if (currentValue.getData() == number) {
                    Node nextValue = currentValue.getNext();
                    Node previousValue = currentValue.getPrevious();

                    nextValue.setPrevious(previousValue);
                    previousValue.setNext(nextValue);
                    size--;
                    return;
                }
                currentValue = currentValue.getNext();
            }
        }
    }

    /* Как я понимаю, такой функции нет в LinkedList, но оставила её
        для проверки в MyLinkedListNodeIsNestedClassTest.
     Функция некорректно отрабатывает, если в список добавляют 0
    */
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
        return size == 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        Node currentValue = head;
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < size; i++) {
            b.append(currentValue.getData());
            currentValue = currentValue.getNext();
            if (i == size - 1) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
        return "";
    }
}
