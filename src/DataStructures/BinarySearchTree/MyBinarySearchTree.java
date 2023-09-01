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
        if (root.getData() == number) {
            root = getNodeForReplace(root);
        } else {
            removeNode(root, number);
        }
        size = 0;
        checkSize(root);
    }

    private void checkSize(Node node) {
        if (node == null) {
            return;
        }
        size++;
        checkSize(node.getLeft());
        checkSize(node.getRight());
    }

    private Node getNodeForReplace(Node node) {
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

    public int size() {
        return size;
    }

    private void removeNode(Node parent, int number) {
        if (parent == null) {
            return;
        }
        if (parent.getData() > number) {
            if (parent.getLeft() != null && parent.getLeft().getData() == number) {
                Node nodeForDelete = getNodeForReplace(parent.getLeft());
                parent.setLeft(nodeForDelete);
                return;
            }
            removeNode(parent.getLeft(), number);
        } else {
            if (parent.getRight() != null && parent.getRight().getData() == number) {
                Node nodeForDelete = getNodeForReplace(parent.getRight());
                parent.setRight(nodeForDelete);
                return;
            }
            removeNode(parent.getRight(), number);
        }
    }

    private Node removeNodeWithBothChild(Node childToRemove) {
        Node child;
        Node rightNode = childToRemove.getRight();
        if (rightNode.getLeft() != null) {
            Node parentOfNewChild = closeNodeWithoutMinLeftChild(rightNode);
            child = parentOfNewChild.getLeft();
            parentOfNewChild.setLeft(child.getRight());
            child.setLeft(childToRemove.getLeft());
            child.setRight(childToRemove.getRight());
        } else {
            child = rightNode;
            child.setLeft(childToRemove.getLeft());
        }

        return child;
    }

    private Node closeNodeWithoutMinLeftChild(Node node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() != null && node.getLeft().getLeft() == null) {
            return node;
        } else {
            return closeNodeWithoutMinLeftChild(node.getLeft());
        }
    }
}
