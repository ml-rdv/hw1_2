package PracticeClassesInterfacesPackagesModules.MyLinkedListIsGeneric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class MyLinkedListNodeIsNestedClassTest {

    @Test
    void should_search_integer_in_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(7);

        var expectedElement = myLinkList.search(2);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_search_String_in_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("B");
        myLinkList.insertAtHead("C");

        var expectedElement = myLinkList.search("B");

        Assertions.assertEquals(expectedElement, "B");
    }

    @Test
    void should_not_search_integer_in_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(7);

        var expectedElement = myLinkList.search(11);

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_not_search_String_in_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("B");
        myLinkList.insertAtHead("C");

        var expectedElement = myLinkList.search("D");

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_insert_integers_to_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(1);
        myLinkList.insertAtEnd(3);
        myLinkList.insertAtEnd(4);
        myLinkList.insertAtEnd(5);
        myLinkList.insertAtEnd(6);

        var expectedElement = myLinkList.toString();

        Assertions.assertEquals(expectedElement, "[1, 2, 3, 4, 5, 6]");
    }

    @Test
    void should_insert_Strings_to_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("B");
        myLinkList.insertAtEnd("C");
        myLinkList.insertAtEnd("D");
        myLinkList.insertAtEnd("I");
        myLinkList.insertAtEnd("F");
        myLinkList.insertAtEnd("G");

        var expectedElement = myLinkList.toString();

        Assertions.assertEquals(expectedElement, "[B, A, C, D, I, F, G]");
    }

    @Test
    void should_delete_last_integer_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtEnd(54);
        myLinkList.delete(2);

        var expectedElement = myLinkList.search(2);

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_delete_last_String_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("B");
        myLinkList.insertAtEnd("C");
        myLinkList.delete("A");

        var expectedElement = myLinkList.search("A");

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_delete_only_one_integer_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(54);
        myLinkList.delete(2);

        var expectedElement = myLinkList.search(2);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_delete_only_one_String_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("C");
        myLinkList.insertAtHead("D");
        myLinkList.delete("A");

        var expectedElement = myLinkList.search("A");

        Assertions.assertEquals(expectedElement, "A");
    }

    @Test
    void should_delete_at_head_integer_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(54);
        myLinkList.deleteAtHead();

        var expectedElement = myLinkList.search(54);

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_delete_at_head_String_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("C");
        myLinkList.deleteAtHead();

        var expectedElement = myLinkList.search("C");

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_delete_integer_in_middle_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<Integer> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtEnd(54);
        myLinkList.insertAtEnd(5);
        myLinkList.insertAtEnd(7);
        myLinkList.delete(2);

        var expectedElement = myLinkList.search(2);

        Assertions.assertNull(expectedElement);
    }

    @Test
    void should_delete_String_in_middle_from_bidirectionalLinkedList() {
        MyLinkedListIsGeneric<String> myLinkList = new MyLinkedListIsGeneric<>();
        myLinkList.insertAtHead("A");
        myLinkList.insertAtHead("B");
        myLinkList.insertAtEnd("C");
        myLinkList.insertAtEnd("B");
        myLinkList.insertAtEnd("D");
        myLinkList.delete("A");

        var expectedElement = myLinkList.search("A");

        Assertions.assertNull(expectedElement);
    }
}

