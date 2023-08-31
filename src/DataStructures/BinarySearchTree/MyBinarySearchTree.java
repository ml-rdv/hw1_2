package DataStructures.BinarySearchTree;

public class MyBinarySearchTree {
    private Node root;
    private int size;

    public MyBinarySearchTree() {
        size = 0;
    }

    public void insert(int number) {
        if (isEmpty()) {
            addRoot(number);
            return;
        }
        addNode(root, number);
    }

    private void addRoot(int number) {
        root = new Node(number, null, null);
        size++;
    }

    private void addNode(Node node, int number) {
        if (node.getData() == number) {
            return;
        }
        if (node.getData() > number) {
            if (node.getLeft() == null) {
                Node newNode = new Node(number, null, null);
                node.setLeft(newNode);
                size++;
                return;
            }
            addNode(node.getLeft(), number);
        } else if (node.getData() < number) {
            if (node.getRight() == null) {
                Node newNode = new Node(number, null, null);
                node.setRight(newNode);
                size++;
                return;
            }
            addNode(node.getRight(), number);
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void printBinarySearchTree(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }

        stringBuilder.append(node.getData()).append(' ');

        printBinarySearchTree(node.getLeft(), stringBuilder);
        printBinarySearchTree(node.getRight(), stringBuilder);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        printBinarySearchTree(root, stringBuilder);
        return stringBuilder.toString();
    }

    public int search(int searchNumber) {
        Node searchNode = recursiveSearch(root, searchNumber);
        return searchNode == null ? -1 : searchNode.getData();
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

    public void remove(int number) {
        //root
        removeNode(root, number);
    }

    private Node getNodeForDelete(Node node) {
        boolean hasNotChildren = node.getLeft() == null && node.getRight() == null;
        boolean hasLeftChild = node.getLeft() != null && node.getRight() == null;
        boolean hasRightChild = node.getLeft() == null && node.getRight() != null;

        if (hasNotChildren) {
           return null;
        } else if (hasLeftChild) {
           return node.getLeft();
        } else if (hasRightChild) {
            return node.getRight();
        }

        return removeNodeWithBothChild(node);
    }

    private Node removeNodeWithBothChild(Node childToRemove) {
        Node parentOfChildToRemove = closeNodeWithoutMinChild(childToRemove.getRight());
        if (parentOfChildToRemove == null) {
           return null;
        }
        Node child = parentOfChildToRemove.getLeft();
        Node newChild = child.getRight();
        parentOfChildToRemove.setLeft(newChild);
        child.setLeft(childToRemove.getLeft());
        child.setRight(childToRemove.getRight());

        return child;
    }

    private Node closeNodeWithoutMinChild(Node node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() != null && node.getLeft().getLeft() == null) {
            return node;
        } else {
            return closeNodeWithoutMinChild(node.getLeft());
        }
    }

    private boolean removeNode(Node node, int number) {
        if (node == null) {
            return false;
        }
        if (node.getData() > number) {
            if (node.getLeft() != null && node.getLeft().getData() == number) {
                Node nodeForDelete = getNodeForDelete(node.getLeft());
                node.setLeft(nodeForDelete);
                return true;
            }
            removeNode(node.getLeft(), number);
        } else {
            if (node.getRight() != null && node.getRight().getData() == number) {
                Node nodeForDelete = getNodeForDelete(node.getRight());
                node.setRight(nodeForDelete);
                return true;
            }
            removeNode(node.getRight(), number);
        }
        return false;
    }
}
