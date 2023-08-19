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

    public void remove(int number) {
        Node parent = findParent(root, number);
        Node childToRemove;
        boolean itIsLeftChild = false;

        if (parent.getLeft() != null && parent.getLeft().getData() == number) {
            childToRemove = parent.getLeft();
            itIsLeftChild = true;
        } else if (parent.getRight() != null && parent.getRight().getData() == number) {
            childToRemove = parent.getRight();
        } else {
            return;
        }

        if (childToRemove.getLeft() == null && childToRemove.getRight() == null) {
            removeNodeWithoutChild(parent, itIsLeftChild);
        } else if (childToRemove.getLeft() != null && childToRemove.getRight() == null) {
            removeNodeWithLeftChild(parent, childToRemove, itIsLeftChild);
        } else if (childToRemove.getLeft() == null && childToRemove.getRight() != null) {
            removeNodeWithRightChild(parent, childToRemove, itIsLeftChild);
        } else {
            removeNodeWithBothChild(parent, childToRemove, itIsLeftChild);
        }
    }

    private void removeNodeWithBothChild(Node parent, Node childToRemove, boolean itIsLeftChild) {
        Node node = closeNodeWithoutMinChild(childToRemove.getRight());
        if (node == null) {
            if (itIsLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else {
            Node child = node.getLeft();
            node.setLeft(null);
            if (itIsLeftChild) {
                child.setLeft(childToRemove.getLeft());
                child.setRight(childToRemove.getRight());
                parent.setLeft(child);
            } else {
                child.setLeft(childToRemove.getLeft());
                child.setRight(childToRemove.getRight());
                parent.setRight(child);
            }
        }
    }

    private void removeNodeWithRightChild(Node parent, Node childToRemove, boolean itIsLeftChild) {
        if (itIsLeftChild) {
            parent.setLeft(childToRemove.getRight());
        } else {
            parent.setRight(childToRemove.getRight());
        }
    }

    private void removeNodeWithLeftChild(Node parent, Node childToRemove, boolean itIsLeftChild) {
        if (itIsLeftChild) {
            parent.setLeft(childToRemove.getLeft());
        } else {
            parent.setRight(childToRemove.getLeft());
        }
    }

    private void removeNodeWithoutChild(Node parent, boolean itIsLeftChild) {
        if (itIsLeftChild) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
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

    private Node findParent(Node node, int number) {
        if (node == null) {
            return null;
        }
        if (node.getData() == number) {
            return node;
        }
        if (node.getData() > number) {
            if (node.getLeft() != null && node.getLeft().getData() == number) {
                return node;
            }
            return findParent(node.getLeft(), number);
        } else {
            if (node.getRight() != null && node.getRight().getData() == number) {
                return node;
            }
            return findParent(node.getRight(), number);
        }
    }
}
