import java.util.Arrays;

/**
 * @author Ethan Yu
 */
public class MergeTester_Yu {
    final static int MIN_SIZE = 900, MAX_SIZE = 1100, MIN_VAL = 1, MAX_VAL = 10000;
    public static void main(String[] args) {
        int[] a = genArray();
        int[] b = genArray();
        System.out.println("Array 1: " + Arrays.toString(a));
        System.out.println("Array 2: " + Arrays.toString(b));
        System.out.println("Merged Array: " + Arrays.toString(merge(a, b)));
    }

    /**
     * generates int[] within size between MIN_SIZE and MAX_SIZE and values between MIN_VAL and MAX_VAL
     * @return generated int[]
     */
    static int[] genArray() {
        int[] ret = new int[(int) (Math.random()*(MAX_SIZE - MIN_SIZE - 1) + 1 + MIN_SIZE)];
        ret[0] = (int) (Math.random() * (MAX_VAL - MIN_VAL) / ret.length);
        for (int i = 1; i < ret.length; i++) ret[i] = ret[i - 1] + (int) (Math.random() * (MAX_VAL - MIN_VAL) / ret.length);
        return ret;
    }

    /**
     * merges int[] a and int[] b to new int[] in sorted order
     * @param a first int[] to be merged
     * @param b second int[] to be merged
     * @return sorted int[] that merges a and b
     */
    static int[] merge(int[] a, int[] b) {
        int[] ret = new int[a.length + b.length];
        int curr = -1, i = 0, j = 0;
        while (++curr < ret.length && (i < a.length || j < b.length)) {
            if (i < a.length && j >= b.length) ret[curr] = a[i++];
            else if (j < b.length && i >= a.length) ret[curr] = b[j++];
            else if (a[i] > b[j])
                ret[curr] = b[j++];
            else ret[curr] = a[i++];
        }
        return ret;
    }
}
