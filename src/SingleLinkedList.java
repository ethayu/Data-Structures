import java.util.NoSuchElementException;

class SingleLinkedList<E> {


    private Node first;
    private Node last;

    class Node 	{
        public E data;
        public Node next;

        /**
         * constructor for Node class
         * @param data the value of the Node
         * @param next Node the node points to
         */
        Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public SingleLinkedList() {
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
        return element;
    }

    /**
     * Adds an element to the front of the linked list.
     * @param element the data to store in the linked list
     */
    public void addFirst(E element) {
        Node newNode = new Node(element, first);
        first = newNode;
        if (first.next == null) last = first;
    }

    /**
     * Adds an element to the end of the linked list
     * @param element the data to store in the linked list
     */
    public void addLast(E element) {
        Node newNode = new Node(element, null);
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
        curr.next = new Node(element, curr.next);
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

}