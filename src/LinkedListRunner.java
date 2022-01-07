import java.util.NoSuchElementException;

class LinkedList
{
    private Node first;

    public LinkedList() { first = null; }

    public Object getFirst()
    {
        if (first == null) { throw new NoSuchElementException(); }
        return first.data;
    }

    public void addFirst(Object element)
    {
        Node aNode = new Node();
        aNode.data = element;
        aNode.next = first;
        first = aNode;

    }

    public Object removeFirst()
    {
        // put your code here
    }

    public String toString()
    {
        String temp = "";
        Node current = first;
        while (current != null)
        {
            temp = temp + current.data.toString() + '\n';
            current = current.next;
        }
        return temp;
    }

    class Node
    {
        public Object data;
        public Node next;
    }
}

public class LinkedListRunner
{
    public static void main(String[] args)
    {
        LinkedList myList = new LinkedList();
        myList.addFirst("bellarmine");
        myList.addFirst("francis");
        myList.addFirst("ignatius");
        myList.addFirst("xavier");
        System.out.println(myList);
        System.out.println("Removed element:  " + myList.removeFirst());
        System.out.println("Removed element:  " + myList.removeFirst());
        System.out.println(myList);
    }
}
 