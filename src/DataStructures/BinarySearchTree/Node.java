package DataStructures.BinarySearchTree;

public class Node {
    private int data;
    private Node left = null;
    private Node right = null;

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
