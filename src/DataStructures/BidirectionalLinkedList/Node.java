package DataStructures.BidirectionalLinkedList;

public class Node implements Cloneable  {
    private int data;
    private Node previous;
    private Node next;

    public Node cloneNode() throws CloneNotSupportedException {
        return (Node) this.clone();
    }
    public int getData(){
        return data;
    }

    public Node getPrevious(){
        return previous;
    }

    public Node getNext(){
        return next;
    }

    public void setData(int data){
        this.data = data;
    }

    public void setPrevious(Node previous){
        this.previous = previous;
    }

    public void setNext(Node next){
        this. next = next;
    }
}
