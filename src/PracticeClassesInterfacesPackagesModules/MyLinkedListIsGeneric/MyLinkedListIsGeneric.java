package PracticeClassesInterfacesPackagesModules.MyLinkedListIsGeneric;

/**
 * Перевести связанный список на Generic.
 */
public class MyLinkedListIsGeneric<E> {

    private Node head;
    private Node tail;
    private int size = 0;

    public void insertAtHead(E data) {
        if (isEmpty()) {
            addHead(data);
        } else {
            Node newElement = new Node(data, null, head);
            head.setPrevious(newElement);
            head = newElement;
            size++;
        }
    }

    private void addHead(E data) {
        head = new Node(data);
        size++;
        tail = head;
    }

    public void insertAtEnd(E data) {
        if (isEmpty()) {
            addHead(data);
        } else {
            Node newElement = new Node(data, tail, null);
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


    public void delete(E data) {
        if (isEmpty()) {
            return;
        }
        if (data.equals(head.getData()) && data.equals(tail.getData())) {
            head = null;
            tail = null;
            size--;
        } else if (data.equals(head.getData())) {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        } else if (data.equals(tail.getData())) {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        } else {
            Node currentValue = head;
            while (true) {
                if (currentValue == null) {
                    return;
                }
                if (currentValue.getData().equals(data)) {
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
     Функция некорректно отрабатывает, если в список добавляют объект null
    */
    public E search(E data) {
        if (isEmpty()) {
            return null;
        }
        Node returnValue = head;
        while (returnValue != null && !returnValue.getData().equals(data)) {
            returnValue = returnValue.getNext();
        }
        return returnValue == null ? null : (E) returnValue.getData();
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
            b.append(currentValue.getData().toString());
            currentValue = currentValue.getNext();
            if (i == size - 1) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
        return "";
    }
}


