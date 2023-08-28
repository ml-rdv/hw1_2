package DataStructures.BinarySearchTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyBinarySearchTreeTest {

    @Test
    void should_insert_element_to_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 3 2 6 5 10 ");
    }

    @Test
    void should_not_insert_element_to_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(5);
        myBiSearchTree.insert(5);
        myBiSearchTree.insert(5);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 3 2 6 5 10 ");
    }

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

        Assertions.assertEquals(expectedElement, -1);
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
    void should_remove_element_with_both_child_second_version_from_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(70);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(100);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(50);
        myBiSearchTree.insert(30);
        myBiSearchTree.insert(40);
        myBiSearchTree.insert(35);
        myBiSearchTree.insert(38);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "70 3 2 50 30 40 35 38 100 ");

        myBiSearchTree.remove(3);
        var afterRemove = myBiSearchTree.toString();

        Assertions.assertEquals(afterRemove, "70 30 2 50 40 35 38 100 ");

    }

    @Test
    void should_remove_element_with_both_child_third_version_from_binarySearchTree() {
        var myBiSearchTree = new MyBinarySearchTree();
        myBiSearchTree.insert(8);
        myBiSearchTree.insert(3);
        myBiSearchTree.insert(10);
        myBiSearchTree.insert(2);
        myBiSearchTree.insert(6);
        myBiSearchTree.insert(4);
        myBiSearchTree.insert(5);
        var expectedElement = myBiSearchTree.toString();

        Assertions.assertEquals(expectedElement, "8 3 2 6 4 5 10 ");

        myBiSearchTree.remove(3);
        var afterRemove = myBiSearchTree.toString();

        Assertions.assertEquals(afterRemove, "8 4 2 6 5 10 ");

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
