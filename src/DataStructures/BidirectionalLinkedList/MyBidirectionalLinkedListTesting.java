package DataStructures.BidirectionalLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyBidirectionalLinkedListTesting {

    @Test
    void should_search_element_in_bidirectionalLinkedList() {
        var myBiLinkList = new MyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtHead(7);

        var expectedElement = myBiLinkList.search(2);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_not_search_element_in_bidirectionalLinkedList() {
        var myBiLinkList = new MyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtHead(7);

        var expectedElement = myBiLinkList.search(11);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_delete_last_element_from_bidirectionalLinkedList() {
        var myBiLinkList = new MyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtEnd(54);
        myBiLinkList.delete(2);

        var expectedElement = myBiLinkList.search(2);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_delete_only_one_element_from_bidirectionalLinkedList() {
        var myBiLinkList = new MyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtHead(54);
        myBiLinkList.delete(2);

        var expectedElement = myBiLinkList.search(2);

        Assertions.assertEquals(expectedElement, 2);
    }

    @Test
    void should_delete_at_head_element_from_bidirectionalLinkedList() {
        var myBiLinkList = new MyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtHead(54);
        myBiLinkList.deleteAtHead();

        var expectedElement = myBiLinkList.search(54);

        Assertions.assertEquals(expectedElement, 0);
    }

    @Test
    void should_delete_element_in_middle_from_bidirectionalLinkedList() {
        var myBiLinkList = new MyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtEnd(54);
        myBiLinkList.insertAtEnd(5);
        myBiLinkList.insertAtEnd(7);
        myBiLinkList.delete(2);

        var expectedElement = myBiLinkList.search(2);

        Assertions.assertEquals(expectedElement, 0);
    }
}

