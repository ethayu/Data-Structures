import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ethan Yu
 * The program runs well and handles collisions correctly. While initially testing, there were many bugs.
 * I had to consider adding to a key that already existed in the map. I also had to consider how to add when the index
 * value was null. Overall, this was a good experience. YoHashMap works as expected, albeit with a bad hash function.
 * I realize that this was on purpose, as I was able to easily check collision handling.
 */

public class SetMapA2 {


    static class YoHashMap extends HashMap<String, Integer> {
        private Entry[] map;
        private int size;
        final double loadFactor = 0.75;

        private static class Entry {
            String key;
            Integer value;
            Entry next;

            Entry(String key, Integer value) {
                this.key = key;
                this.value = value;
            }

        }

        /**
         * Returns the hash code for the specified key.
         * @param key the key to hash
         * @return the hash code for the specified key
         */
        private int hash(String key) {
            return key.length() % map.length;
        }

        /**
         * Instantiates a YoHashMap with initial size 10.
         */
        public YoHashMap() {
            map = new Entry[10];
        }

        /**
         * Instantiates a YoHashMap with specified initial size.
         * @param initialCapacity the initial capacity of the map
         */
        public YoHashMap(int initialCapacity) {
            map = new Entry[initialCapacity];
        }

        /**
         * Clears the map.
         */
        @Override
        public void clear() {
            size = 0;
            Arrays.fill(map, null);
        }

        /**
         * Returns whether the map contains the specified key.
         * @param key the key to search for
         * @return whether the map contains the specified key
         */
        public boolean containsKey(String key) {
            return get(key) != null;
        }

        /**
         * Returns the value associated with the specified key.
         * @param key the key to search for
         * @return the value associated with the specified key
         */
        public Integer get(String key) {
            Entry e = map[hash(key)];
            while (e != null) {
                if (e.key.equals(key)) return e.value;
                e = e.next;
            }
            return null;
        }

        /**
         * Returns the set of keys in the map.
         * @return the set of keys in the map
         */
        @Override
        public Set<String> keySet() {
            Set<String> ret = new HashSet<>();
            for (Entry e : map) {
                while (e != null) {
                    ret.add(e.key);
                    e = e.next;
                }
            }
            return ret;
        }

        /**
         * Puts the specified key-value pair into the map.
         * @param key the key to put
         * @param value the value to put
         * @return the previous value associated with the specified key, or null if there was no previous value
         */
        @Override
        public Integer put(String key, Integer value) {
            Entry e = map[hash(key)];
            if (e == null) {
                map[hash(key)] = new Entry(key, value);
                size++;
                return null;
            }
            if (e.key.equals(key)) {
                int ret = e.value;
                e.value = value;
                return ret;
            }
            while (e.next != null) {
                if (e.next.key.equals(key)) {
                    int ret = e.next.value;
                    e.next.value = value;
                    return ret;
                }
                e = e.next;
            }
            e.next = new Entry(key, value);
            size++;
            if (size >= map.length * loadFactor) grow();
            return null;
        }

        /**
         * Grows the map by doubling its size.
         */
        private void grow() {
            size = 0;
            Entry[] newMap = new Entry[map.length * 2];
            for (Entry e : map) {
                while (e != null) {
                    put(e.key, e.value);
                    e = e.next;
                }
            }
            map = newMap;
        }

        /**
         * Removes the specified key from the map.
         * @param key the key to remove
         * @return the value associated with the specified removed key, or null if there was no previous value
         */
        public Integer remove(String key) {
            Entry e = map[hash(key)];
            if (!containsKey(key)) return null;
            if (e.key.equals(key)) {
                size--;
                int ret = e.value;
                map[hash(key)] = e.next;
                return ret;
            }
            while (e.next != null) {
                if (e.next.key.equals(key)) {
                    size--;
                    int ret = e.next.value;
                    e.next = e.next.next;
                    return ret;
                }
            }
            return null;
        }

        /**
         * Returns the number of key-value pairs in the map.
         * @return the number of key-value pairs in the map
         */
        @Override
        public int size() {
            return size;
        }

    }

    public static void main(String[] args) {
        YoHashMap map = new YoHashMap();
        //Test put
        System.out.println("------------------------TESTING PUT------------------------");
        map.put("domey", 1);
        System.out.println("put(\"domey\", 1)");
        map.put("dog", 2);
        System.out.println("put(\"dog\", 2)");
        map.put("cat", 3);
        System.out.println("put(\"cat\", 3)");  //collision

        //Test keySet
        System.out.println("------------------------TESTING KEY SET------------------------");
        System.out.println("keySet(): " + map.keySet());

        //Test get
        System.out.println("------------------------TESTING GET------------------------");
        System.out.println("get(\"domey\"): " + map.get("domey"));
        System.out.println("get(\"dog\"): " + map.get("dog"));
        System.out.println("get(\"cat\"): " + map.get("cat"));
        System.out.println("get(\"Rob\"): " + map.get("Rob"));

        //Test containsKey
        System.out.println("------------------------TESTING CONTAINS KEY------------------------");
        System.out.println("containsKey(\"domey\"): " + map.containsKey("domey"));
        System.out.println("containsKey(\"dog\"): " + map.containsKey("dog"));
        System.out.println("containsKey(\"cat\"): " + map.containsKey("cat"));
        System.out.println("containsKey(\"car\"): " + map.containsKey("car"));

        //Test update
        System.out.println("------------------------TESTING UPDATE------------------------");
        map.put("cat", 4);
        System.out.println("put(\"cat\", 4)");
        System.out.println("get(\"cat\"): " + map.get("cat"));
        System.out.println("keySet(): " + map.keySet());

        //Test remove
        System.out.println("------------------------TESTING REMOVE------------------------");
        System.out.println("removing " + map.remove("domey"));
        System.out.println("remove(\"domey\")");
        System.out.println("get(\"domey\"): " + map.get("domey"));
        map.remove("rome");
        System.out.println("remove(\"rome\")");
        System.out.println("get(\"rome\"): " + map.get("rome"));
        System.out.println("keySet(): " + map.keySet());

        //Test size
        System.out.println("------------------------TESTING SIZE------------------------");
        System.out.println("size(): " + map.size());
        map.put("dome", 5);
        System.out.println("put(\"dome\", 5)");
        System.out.println("size(): " + map.size());

        //Test clear
        System.out.println("------------------------TESTING CLEAR------------------------");
        map.clear();
        System.out.println("clear()");
        System.out.println("size(): " + map.size());
        System.out.println("keySet(): " + map.keySet());
        System.out.println("get(\"dome\"): " + map.get("dome"));

        //Test instantiation with specified initial capacity
        System.out.println("------------------------TESTING INSTANTIATION------------------------");
        map = new YoHashMap(20);
        System.out.println("instantiate with 20: " + map.map.length);
    }
}