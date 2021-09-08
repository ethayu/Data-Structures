public class BellArrayList {
    private int size = 0;
    private String[] array;

    /**
     * creates a new BellArrayList
     */
    public BellArrayList() {
        array = new String[1];
    }

    /**
     * creates a new BellArrayList with given size
     * @param size
     */
    public BellArrayList(int size) {
        array = new String[size];
    }

    /**
     * returns size of BellArrayList
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * returns value at given index
     * @param index
     * @return
     */
    public String get(int index) {
        if (index >= size) throw new ArrayIndexOutOfBoundsException("index " + index + " is greater than size of array " + size);
        return array[index];
    }

    /**
     * adds given item at given index
     * @param index
     * @param item
     */
    public void add(int index, String item) {
        if (index > size) throw new ArrayIndexOutOfBoundsException("index " + index + " is greater than size of array " + size);
        else if (index == size) add(item);
        else {
            for (int i = size - 1; i > index; i--) array[i] = array[i - 1];
            array[index] = item;
            size++;
        }
    }

    /**
     * adds given item to end of BellArrayList
     * @param item
     */
    public void add(String item) {
        if (size == array.length) {
            String[] ret = new String[size * 2];
            for (int i = 0; i < size; i++) ret[i] = array[i];
            array = ret;
        }
        array[size] = item;
        size++;
    }

    /**
     * removes value at given index
     * @param index
     * @return
     */
    public String remove(int index) {
        String ret = array[index];
        if (index >= size) throw new ArrayIndexOutOfBoundsException("index " + index + " is greater than size of array " + size);
        else if (index == size - 1) {
            size--;
            array[size] = null;
        } else {
            for (int i = index; i < size - 2; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
        return ret;
    }

    /**
     * sets value at given index to given item
     * @param index
     * @param item
     * @return
     */
    public String set(int index, String item) {
        String ret = array[index];
        if (index >= size) throw new ArrayIndexOutOfBoundsException("index " + index + " is greater than size of array " + size);
        else array[index] = item;
        return ret;
    }

    /**
     * generic toString() function
     * @return
     */
    @Override
    public String toString() {
        String ret = "";
        for (String s : array) ret += " " + s;
        return "BellArrayList{" +
                "size=" + size +
                ", array=" + ret +
                '}';
    }
}
