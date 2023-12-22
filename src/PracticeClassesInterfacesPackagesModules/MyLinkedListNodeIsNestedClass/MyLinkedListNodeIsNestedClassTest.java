package PracticeClassesInterfacesPackagesModules.MyLinkedListNodeIsNestedClass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyLinkedListNodeIsNestedClassTest {

    @Test
    void should_search_element_in_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(7);

        var expectedElement = myLinkList.search(2);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_not_search_element_in_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(7);

        var expectedElement = myLinkList.search(11);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_insert_elements_to_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
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
    void should_delete_last_element_from_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtEnd(54);
        myLinkList.delete(2);

        var expectedElement = myLinkList.search(2);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_delete_only_one_element_from_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(54);
        myLinkList.delete(2);

        var expectedElement = myLinkList.search(2);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_delete_at_head_element_from_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtHead(54);
        myLinkList.deleteAtHead();

        var expectedElement = myLinkList.search(54);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_delete_element_in_middle_from_bidirectionalLinkedList() {
        var myLinkList = new MyLinkedListNodeIsNestedClass();
        myLinkList.insertAtHead(2);
        myLinkList.insertAtHead(5);
        myLinkList.insertAtEnd(54);
        myLinkList.insertAtEnd(5);
        myLinkList.insertAtEnd(7);
        myLinkList.delete(2);

        var expectedElement = myLinkList.search(2);

        Assertions.assertEquals(expectedElement, 0);
    }
}

