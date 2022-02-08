import java.util.Stack;

class BinaryConverter {
    /**
     * printBinary converts a decimal integer to its binary equivalent (and displays it to standard output).
     * preconditon: decimal > 0
     */
    public static void printBinary(int decimal) {
        Stack<Integer> binary = new Stack<>();
        while (decimal != 0) {
            binary.add(decimal % 2);
            decimal /= 2;
        }
        String ret = "";
        while (!binary.isEmpty()) ret += binary.pop();
        System.out.println(ret);

    }

    public static void main(String[] a) {

        int i = 10;
        printBinary(10);
        printBinary(9);
        printBinary(8);
    }
}
