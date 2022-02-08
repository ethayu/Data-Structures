import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SetMapA1 {

    static boolean useMAD = false; //whether to use MAD compression

    /**
     * This class implements a hash set using separate chaining.
     */
    public static class HashSet {
        private Node[] buckets;
        private int currentSize, collisions = 0;

        public boolean isPrime(int num) { //sees if input is prime
            for (int i = 2; i <= num / 2; ++i) {
                if (num % i == 0) {
                    return true;
                }
            }
            return false;
        }

        //E16.20
        public int MAD(int h) {
            int a = 3, b = 5; //from E16.21
            int p = buckets.length + 1;
            while (!isPrime(p)) p++;
            return Math.abs(((a * h + b) % p) % buckets.length);
        }

        /**
         * Constructs a hash table.
         *
         * @param bucketsLength the length of the buckets array
         */
        public HashSet(int bucketsLength) {
            buckets = new Node[bucketsLength];
            currentSize = 0;
        }

        /**
         * Tests for set membership.
         *
         * @param x an object
         * @return true if x is an element of this set
         */
        public boolean contains(Object x) {
            int h = useMAD ? MAD(x.hashCode()) : x.hashCode();
            h = h % buckets.length;
            if (h < 0) {
                h = -h;
            }

            Node current = buckets[h];
            while (current != null) {
                if (current.data.equals(x)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        /**
         * Adds an element to this set.
         *
         * @param x an object
         * @return true if x is a new object, false if x was
         * already in the set
         */
        public boolean add(Object x) {
            int h = useMAD ? MAD(x.hashCode()) : x.hashCode();
            if (h < 0) {
                h = -h;
            }
            h = h % buckets.length;
            if (buckets[h] != null) collisions++; //E16.21

            Node current = buckets[h];
            while (current != null) {
                if (current.data.equals(x)) {
                    return false;
                }
                // Already in the set
                current = current.next;
            }
            Node newNode = new Node();
            newNode.data = x;
            newNode.next = buckets[h];
            buckets[h] = newNode;
            currentSize++;
            if (1.0 * currentSize / buckets.length > 1) {//E16.18
                grow();
            }
            return true;
        }

        private void grow() { //E16.18
            currentSize = 0;
            Node[] old = buckets;
            buckets = new Node[old.length * 2];
            for (Node node : old) {
                Node curr = node;
                while (curr != null) {
                    add(curr.data);
                    curr = curr.next;
                }
            }
        }

        private void shrink() { //E16.18
            currentSize = 0;
            Node[] old = buckets;
            buckets = new Node[old.length / 2];
            for (Node node : old) {
                Node curr = node;
                while (curr != null) {
                    add(curr.data);
                    curr = curr.next;
                }
            }
        }

        /**
         * Removes an object from this set.
         *
         * @param x an object
         * @return true if x was removed from this set, false
         * if x was not an element of this set
         */
        public boolean remove(Object x) {
            int h = useMAD ? MAD(x.hashCode()) : x.hashCode(); //E16.21
            if (h < 0) {
                h = -h;
            }
            h = h % buckets.length;

            Node current = buckets[h];
            Node previous = null;
            while (current != null) {
                if (current.data.equals(x)) {
                    if (previous == null) {
                        buckets[h] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    currentSize--;
                    return true;
                }
                previous = current;
                current = current.next;
            }
            if (1.0 * currentSize / buckets.length < 0.5) { //E16.18
                shrink();
            }
            return false;
        }

        /**
         * Returns an iterator that traverses the elements of this set.
         *
         * @return a hash set iterator
         */
        public Iterator iterator() {
            return new HashSetIterator();
        }

        /**
         * Gets the number of elements in this set.
         *
         * @return the number of elements
         */
        public int size() {
            return currentSize;
        }

        class Node {
            public Object data;
            public Node next;
        }

        class HashSetIterator implements Iterator {
            private int bucketIndex;
            private Node current;

            /**
             * Constructs a hash set iterator that points to the
             * first element of the hash set.
             */
            public HashSetIterator() {
                current = null;
                bucketIndex = -1;
            }

            public boolean hasNext() {
                if (current != null && current.next != null) {
                    return true;
                }
                for (int b = bucketIndex + 1; b < buckets.length; b++) {
                    if (buckets[b] != null) {
                        return true;
                    }
                }
                return false;
            }

            public Object next() {
                if (hasNext()) {
                    current = current.next; // Move to next element in bucket //E16.17
                } else // Move to next bucket
                {
                    do {
                        bucketIndex++;
                        if (bucketIndex == buckets.length) {
                            throw new NoSuchElementException();
                        }
                        current = buckets[bucketIndex];
                    }
                    while (current == null);
                }
                return current.data;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //R16.22
        int head = 1, tail = 4, N = 10;
        int currentSize = head - tail % N; //Always Computable
        //R16.23 (a, c only)
        //a. head -> [1, 2, 3, 4, 5] <- tail
        //c. head -> [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] <- tail
        //R16.26
        //Two equal objects would have different hash values, which isn't correct
        //E16.17 (coding required, check with here (Links to an external site.) for starter code)
        //see modified next function above
        //E16.18
        //see modified add, remove, and new grow and shrink methods
        //E16.20
        //see isPrime, MAD functions
        //E16.21
        Scanner sc = new Scanner(new File("message.txt"));
        HashSet noMAD = new HashSet(50);
        HashSet yesMAD = new HashSet(50);

        while (sc.hasNext()) {
            String str = sc.next();
            noMAD.add(str);
            useMAD = true;
            yesMAD.add(str);
            useMAD = false;
        }

        System.out.println("Collisions without MAD: " + noMAD.collisions);
        System.out.println("Collisions with MAD: " + yesMAD.collisions);

    }
}
