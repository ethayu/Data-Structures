import java.util.NoSuchElementException;

class DoubleLinkedList<E> {

    private Node first;
    private Node last;

    class Node 	{
        public E data;
        public Node next;
        public Node prev;

        /**
         * constructor for Node class
         * @param data the value of the Node
         * @param next Node the node points to
         */
        Node(E data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public DoubleLinkedList() {
        first = null;
        last = null;
    }

    /**
     * @return the first element in the linked list
     */
    public E getFirst() {
        if (first == null)
            throw new NoSuchElementException();
        return first.data;
    }

    /**
     * Removes the first element in the linked list.
     * @return the removed element
     */
    public E removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        E element = first.data;
        first = first.next;
        first.prev =  null;
        return element;
    }

    /**
     * Adds an element to the front of the linked list.
     * @param element the data to store in the linked list
     */
    public void addFirst(E element) {
        first = new Node(element, first, null);
        if (first.next == null) last = first;
        else
        first.next.prev = first;
    }

    /**
     * Adds an element to the end of the linked list
     * @param element the data to store in the linked list
     */
    public void addLast(E element) {
        Node newNode = new Node(element, null, last);
        last.next = newNode;
        last = newNode;
    }

    /**
     * Removes last element in LinkedList
     * @return previous last element
     */
    public E removeLast() {
        if (first == null)
            throw new NoSuchElementException();
        Node curr = first;
        while (curr.next != last) curr = curr.next;
        E element = last.data;
        curr.next = null;
        last = curr;
        return element;
    }

    /**
     * returns value at node index
     * @param index of node value is to be returned of
     * @return value at node index
     */
    E get(int index) {
        int i = -1;
        Node curr = first;
        while (++i != index) {
            curr = curr.next;
            if (curr == null)
                throw new NoSuchElementException();
        }
        return curr.data;
    }

    /**
     * adds element at index
     * @param index index to add element at
     * @param element element to add at index
     */
    void add(int index, E element) {
        int i = 0;
        Node curr = first;
        if (index == 0) {
            addFirst(element);
            return;
        }
        while (++i != index) {
            curr = curr.next;
            if (curr == null)
                throw new NoSuchElementException();
        }
        if (curr.next == null) {
            addLast(element);
            return;
        }
        curr.next.prev = new Node(element, curr.next, curr);
        curr.next = curr.next.prev;
    }

    /**
     * removes node at given index
     * @param index to remove node
     * @return value of removed node
     */
    E remove(int index) {
        int i = 0;
        Node curr = first;
        while (++i != index) {
            curr = curr.next;
            if (curr == null)
                throw new NoSuchElementException();
        }
        if (curr.next == null) return null;
        E element = curr.next.data;
        curr.next = curr.next.next == null ? null : curr.next.next;
        return element;
    }

    /**
     * @return size of LinkedList
     */
    int size() {
        int i = 0;
        if (first == null) return i;
        Node curr = first;
        while (curr.next != last) {
            curr = curr.next;
            i++;
        }
        return i + 2;
    }

    /**
     * Reverses given DoubleLinkedList
     */
    void reverse() {
        int size = size();
        int counter = 0;
        DoubleLinkedList<E> ret = new DoubleLinkedList<>();
        Node head = first;
        Node tail = last;
        while (counter++ < size / 2) {
            E temp = head.data;
            head.data = tail.data;
            tail.data = temp;
            head = head.next;
            tail = tail.prev;
        }
    }


    /**
     * converts LinkedList to String
     * @return String of LinkedList values
     */
    public String toString() {
        String ret = "";
        ret += '[';
        Node curr = first;
        while (curr.next != last) {
            ret += curr.data + ", ";
            curr = curr.next;
        }
        ret += curr.data + ", " + last.data + "]";
        return ret;
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        // test addFirst
        System.out.println("TESTING ADD FIRST--------------------------------");
        list.addFirst(1); System.out.println("addFirst(1)");
        list.addFirst(2); System.out.println("addFirst(2)");
        list.addFirst(3); System.out.println("addFirst(3)");
        System.out.println("List: " + list);

        // test addLast
        System.out.println("TESTING ADD LAST---------------------------------");
        list.addLast(5); System.out.println("addLast(5)");
        list.addLast(6); System.out.println("addLast(6)");
        list.addLast(7); System.out.println("addLast(7)");
        System.out.println("List: " + list);

        // test add
        System.out.println("TESTING ADD--------------------------------------");
        list.add(0, 4); System.out.println("add(0, 4)");
        list.add(3, 5); System.out.println("add(3, 5)");
        list.add(5, 6); System.out.println("add(5, 6)");
        System.out.println("List: " + list);

        // test get
        System.out.println("TESTING GET--------------------------------------");
        System.out.println("get(0): " + list.get(0));
        System.out.println("get(6): " + list.get(6));
        System.out.println("get(7): " + list.get(7));
        System.out.println("List: " + list);

        // test remove
        System.out.println("TESTING REMOVE-----------------------------------");
        System.out.println("Removed " + list.removeFirst() + " as first element");
        System.out.println("Removed " + list.removeLast() + " as last element");
        System.out.println("Removed " + list.remove(4) + " as element at index 4");
        System.out.println("List: " + list);

        // test size
        System.out.println("TESTING SIZE-------------------------------------");
        System.out.println("size(): " + list.size());
        list.removeFirst(); System.out.println("Removed first element.");
        System.out.println("size(): " + list.size());
        list.removeLast(); System.out.println("Removed last element.");
        System.out.println("size(): " + list.size());
        System.out.println("List: " + list);

        // test getFirst
        System.out.println("TESTING GET FIRST--------------------------------");
        System.out.println("getFirst(): " + list.getFirst());
        list.removeFirst(); System.out.println("Removed first element.");
        System.out.println("getFirst(): " + list.getFirst());
        System.out.println("List: " + list);

        //test reverse
        list.addLast(9);
        list.addLast(3);
        System.out.println("TESTING REVERSE--------------------------------");
        System.out.println("Before reverse: " + list);
        list.reverse();
        System.out.println("After reverse: " + list);
    }
}