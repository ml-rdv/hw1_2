package DataStructures.BinarySearchTree;

public class MyBinarySearchTreeTesting {
    private static MyBinarySearchTree buildBinarySearchTree() {
        return new MyBinarySearchTree();
    }

    public static void main(String[] args) {
        MyBinarySearchTree myBiLinkList = buildBinarySearchTree();
        myBiLinkList.insert(8);
        myBiLinkList.insert(3);
        myBiLinkList.insert(10);
        myBiLinkList.insert(2);
        myBiLinkList.insert(6);
        System.out.println(myBiLinkList);
        myBiLinkList.insert(11);
        myBiLinkList.insert(3);
        System.out.println(myBiLinkList);
        System.out.println(myBiLinkList.search(10));
        System.out.println(myBiLinkList.search(100));
    }
}
