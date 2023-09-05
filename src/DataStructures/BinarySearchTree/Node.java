package DataStructures.BinarySearchTree;

public class Node {
    private int data;
    private Node left = null;
    private Node right = null;

    public Node() {
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(Node previous) {
        this.left = previous;
    }

    public void setRight(Node next) {
        this.right = next;
    }
}
