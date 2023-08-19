package DataStructures.BinarySearchTree;

public class MyBinarySearchTree {
    private final Node root;
    private int size;

    public MyBinarySearchTree() {
        root = new Node();
        size = 0;
    }

    public void insert(int number) {
        if (isEmpty()) {
            addRoot(number);
        } else {
            Node node = findNode(root, number);
            Node newNode = new Node();
            newNode.setData(number);
            if (node.getData() > number) {
                node.setLeft(newNode);
            } else if (node.getData() < number) {
                node.setRight(newNode);
            }
        }
        size++;
    }

    private void addRoot(int number) {
        root.setData(number);
    }

    private Node findNode(Node node, int number) {
        if (node.getData() == number) {
            return node;
        }
        if (node.getData() > number) {
            if (node.getLeft() == null) {
                return node;
            }
            return findNode(node.getLeft(), number);
        } else {
            if (node.getRight() == null) {
                return node;
            }
            return findNode(node.getRight(), number);
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private StringBuilder printBinarySearchTree(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return stringBuilder;
        } else {
            stringBuilder.append(node.getData()).append(' ');
        }
        printBinarySearchTree(node.getLeft(), stringBuilder);
        printBinarySearchTree(node.getRight(), stringBuilder);
        return stringBuilder;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = printBinarySearchTree(root, stringBuilder);
        return stringBuilder.toString();
    }

    public int search(int searchNumber) {
        if (isEmpty()) {
            return -1;
        }
        Node searchNode = recursiveSearch(root, searchNumber);
        return searchNode == null ? 0 : searchNode.getData();
    }

    private Node recursiveSearch(Node node, int number) {
        if (node == null) {
            return null;
        }
        if (node.getData() == number) {
            return node;
        }
        if (node.getData() > number) {
            return recursiveSearch(node.getLeft(), number);
        } else {
            return recursiveSearch(node.getRight(), number);
        }
    }
}
