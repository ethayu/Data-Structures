public class ArrayAsg1 {

    //variables for questions
    static int[] nums = new int[]{1, 2, 12, 12, 3, 1, 2, 4, 3, 2, 2, 2, 2, 3, 6, 12, 12, 6, 3, 1};
    static int[] nums2 = new int[]{1, 12, 3, 4, 2};

    public static void main(String[] args) {
        System.out.println(R7_23());
        E7_19();
    }

    /**
     * Returns longest length of consecutive integers
     */
    static int R7_23() {
        int prev = -1;
        int max = 0;
        int ret = 0;
        for (int a : nums) {
            if (a == prev)
                ret++;
            else {
                max = Integer.max(max, ret + 1);
                ret = 0;
                prev = a;
            }
        }
        return max;
    }

    //R7.24 method doesn't change value of values[]

    /**
     * Returns a bar graph, with largest value scaled to 20 asterisks
     */
    static void E7_19() {
        int max = Integer.MIN_VALUE;
        for (int a : nums2) max = Integer.max(max, a);
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = nums2[i] * 20 / max;
        }
        for (int i = 20; i > 0; i--) {
            for (int a : nums2) if (a >= i) System.out.print('*'); else System.out.print(' ');
            System.out.println();
        }
    }
}
