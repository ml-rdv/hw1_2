package DataStructures.BidirectionalLinkedList;

public class MyBidirectionalLinkedListTesting {

    private static MyBidirectionalLinkedList buildMyBidirectionalLinkedList() {
        return new MyBidirectionalLinkedList();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        MyBidirectionalLinkedList myBiLinkList = buildMyBidirectionalLinkedList();
        myBiLinkList.insertAtHead(2);
        myBiLinkList.insertAtHead(5);
        myBiLinkList.insertAtHead(54);
        myBiLinkList.insertAtHead(4);
        System.out.println(myBiLinkList);
        myBiLinkList.delete(-5);
        myBiLinkList.InsertAtEnd(-5);
        System.out.println(myBiLinkList.search(2));
        System.out.println(myBiLinkList.search(-5));
        System.out.println(myBiLinkList);
    }

}

