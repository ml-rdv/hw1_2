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
                insertNode(node, number, true);
                return;
            }
            addNode(node.getLeft(), number);
        } else if (node.getData() < number) {
            if (node.getRight() == null) {
                insertNode(node, number, false);
                return;
            }
            addNode(node.getRight(), number);
        }
    }

    private void insertNode(Node node, int number, boolean addLeftChild) {
        Node newNode = new Node(number, null, null);
        if (addLeftChild) {
            node.setLeft(newNode);
        } else {
            node.setRight(newNode);
        }
        size++;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void printBinarySearchTree(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        } else {
            stringBuilder.append(node.getData()).append(' ');
        }
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
        Node parent = findParent(root, number);
        Node childToRemove;
        boolean itIsLeftChild;

        if (parent.getLeft() != null && parent.getLeft().getData() == number) {
            childToRemove = parent.getLeft();
            itIsLeftChild = true;
        } else if (parent.getRight() != null && parent.getRight().getData() == number) {
            childToRemove = parent.getRight();
            itIsLeftChild = false;
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
        size--;
    }

    private void removeNodeWithBothChild(Node parent, Node childToRemove, boolean itIsLeftChild) {
        Node node = closeNodeWithoutMinChild1(childToRemove.getRight());
        if (node == null) {
            if (itIsLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else {
            Node child = node.getLeft();
            Node newChild = child.getRight();
            node.setLeft(newChild);
            child.setLeft(childToRemove.getLeft());
            child.setRight(childToRemove.getRight());
            if (itIsLeftChild) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
    }

    private Node closeNodeWithoutMinChild1(Node node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() != null && node.getLeft().getLeft() == null) {
            return node;
        } else {
            return closeNodeWithoutMinChild(node.getLeft());
        }
    }

    private void removeNodeWithRightChild(Node parent, Node childToRemove, boolean itIsLeftChild) {
        removeNode(parent, itIsLeftChild, childToRemove, false);
    }

    private void removeNodeWithLeftChild(Node parent, Node childToRemove, boolean itIsLeftChild) {
        removeNode(parent, itIsLeftChild, childToRemove, true);
    }

    private void removeNodeWithoutChild(Node parent, boolean itIsLeftChild) {
        removeNode(parent, itIsLeftChild, null, false);
    }

    private void removeNode(Node parent, boolean itIsLeftChild, Node childToRemove, boolean nodeWithLeftChild) {
        Node child = null;
        if (childToRemove != null) {
            if (nodeWithLeftChild) {
                child = childToRemove.getLeft();
            } else {
                child = childToRemove.getRight();
            }
        }
        if (itIsLeftChild) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
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
