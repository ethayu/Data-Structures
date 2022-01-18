import java.util.EmptyStackException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Ethan Yu
 */

class LinkedList {
    private Node first;

    public LinkedList() {
        first = null;
    }

    public Object getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public void addFirst(Object element) {
        Node aNode = new Node();
        aNode.data = element;
        aNode.next = first;
        first = aNode;

    }

    /**
     * 2
     * removes first element of list
     * @return element of removed first element
     */
    public Object removeFirst() {
        if (first == null) throw new NoSuchElementException();
        Object ret = first.data;
        first = first.next;
        return ret;
    }

    public String toString() {
        String temp = "";
        Node current = first;
        while (current != null) {
            temp = temp + current.data.toString() + '\n';
            current = current.next;
        }
        return temp;
    }

    class Node {
        public Object data;
        public Node next;
    }

    public ListIterator listIterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements ListIterator {
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        public LinkedListIterator() {
            position = null;
            previous = null;
            isAfterNext = false;
        }

        /**
         * 3
         * iterates to next element and returns value
         * @return
         */
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            previous = position;
            isAfterNext = true;
            position = position == null ? first : position.next;
            return position.data;
        }

        /**
         * ignore next 4 methods - jdk gets mad if I don't include these methods while implementing ListIterator.
         */
        public boolean hasPrevious() {
            return false;
        }

        public Object previous() {
            return null;
        }

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return 0;
        }

        public boolean hasNext() {
            if (position == null) {
                return first != null;
            } else {
                return position.next != null;
            }
        }

        /**
         * 4
         * Removes value at iterator
         */
        public void remove() {
            if (!isAfterNext) throw new IllegalStateException();
            if (position == first) removeFirst();
            else previous.next = position.next;
            position = previous;
            isAfterNext = false;
        }

        /**
         * 5
         * adds an element at the current iterator location
         * @param element to be added
         */
        public void add(Object element) {
            Node temp = new Node();
            temp.data = element;
            if (position == null) {
                addFirst(element);
                position = first;
            } else {
                temp.next = position.next;
                position.next = temp;
                position = temp;
            }
            isAfterNext = false;
        }

        /**
         * 6
         * Sets value to parameter at current iterator
         * @param element to be set
         */
        public void set(Object element) {
            if (!isAfterNext) throw new IllegalStateException();
            position.data = element;
        }
    }

}

/**
 * 8
 * Stack implementation via array
 */
class StackArray {
    private Object[] item; // The array where elements are stored
    private int open = 0;  // The index of the first empty location in the stack
    private int size = 2;  // The current number of item locations in the stack

    /**
     * Constructs an empty stack.
     */
    public StackArray() {
        item = new Object[size];
    }

    /**
     * adds element to array; if array too small array size is doubled
     * @param element to be added to array
     */
    public void push(Object element) {
        if (open == size) {
            Object[] temp = new Object[size * 2];
            for (int i = 0; i < size; i++) temp[i] = item[i];
            item = temp;
            item[open++] = element;
            size *= 2;
        } else {
            item[open++] = element;
        }
    }

    /**
     * removes element in array and returns value
     * @return value of removed element
     */
    public Object pop() {
        if (open == 0) return new EmptyStackException();
        Object ret = item[--open];
        item[open] = null;
        return ret;
    }

    /**
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return open == 0;
    }

    public String toString() {
        if (open == 0) {
            return "[]";
        }
        String temp = "[" + item[0];
        int i = 1;
        while (i < open) {
            temp = temp + ", " + item[i];
            i = i + 1;
        }
        temp = temp + "]";
        return temp;
    }
}

public class LinkedListandStackArrayRunner {
    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.addFirst("bellarmine");
        myList.addFirst("francis");
        myList.addFirst("ignatius");
        myList.addFirst("xavier");
        System.out.println(myList);
        System.out.println("Removed element:  " + myList.removeFirst());
        System.out.println("Removed element:  " + myList.removeFirst());
        System.out.println(myList);
        System.out.println("____________________________________");
        System.out.println();
        StackArray sa = new StackArray();
        sa.push("a");
        sa.push("b");
        sa.push("c");
        sa.push("d");
        sa.push("e");
        System.out.println(sa);
        System.out.println(sa.pop());
        System.out.println(sa);
        System.out.println(sa.pop());
        System.out.println(sa);
        System.out.println(sa.pop());
        System.out.println(sa);
        System.out.println(sa.pop());
        System.out.println(sa);
        System.out.println(sa.pop());
        System.out.println(sa);
        System.out.println(sa.pop());
    }
}
 