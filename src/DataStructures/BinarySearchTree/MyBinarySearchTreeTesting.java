package DataStructures.BinarySearchTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyBinarySearchTreeTesting {

    @Test
    void should_search_element_in_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        var expectedElement = myBiSearchTree.search(10);

        Assertions.assertEquals(expectedElement, 10);
    }

    @Test
    void should_not_search_element_in_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        var expectedElement = myBiSearchTree.search(100);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_remove_element_with_both_child_from_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        myBiSearchTree.remove(3);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 5 2 6 10 ");
    }

    @Test
    void should_remove_element_without_child_from_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        myBiSearchTree.remove(5);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 3 2 6 10 ");
    }

    @Test
    void should_remove_element_with_left_child_from_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        myBiSearchTree.remove(6);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 3 2 5 10 ");
    }

    @Test
    void should_remove_element_with_right_child_from_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        myBiSearchTree.insert(11);
        myBiSearchTree.remove(10);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 3 2 6 5 11 ");
    }
}
