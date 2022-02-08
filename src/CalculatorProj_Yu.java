import java.util.*;
import java.util.LinkedList;

public class CalculatorProj_Yu {

    static YoStack<String> calculate; //To store numbers during calculation

    /**
     * ranks operations in order of PEMDAS
     *
     * @param operation character type
     * @return integer of precedence
     */
    static int precedence(char operation) {
        if (operation == '+' || operation == '-') {
            return 1;
        } else if (operation == '*' || operation == '/') {
            return 2;
        } else if (operation == '^') {
            return 3;
        } else if (operation == '(' || operation == ')') {
            return 0;
        } else return -1;
    }

    /**
     * converts String operation to character
     *
     * @param operation String to convert
     * @return converted character
     */
    static char toChar(String operation) {
        return switch (operation) {
            case "+" -> '+';
            case "-" -> '-';
            case "*" -> '*';
            case "/" -> '/';
            case "^" -> '^';
            default -> 'N';
        };
    }

    /**
     * returns math operation given numbers and operation
     *
     * @param b         number 1
     * @param a         number 2
     * @param operation to perform
     * @return output
     */
    static double process(double b, double a, String operation) {
        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "^" -> Math.pow(a, b);
            default -> 0;
        };
    }

    public static void main(String[] args) {
        try {
            System.out.print("Enter your expression: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            Queue<String> postfix = new LinkedList<>();
            YoStack<Character> operators = new LinkedStack<>();
            String ret = "";
            boolean hadOperator = true;
            for (char c : str.toCharArray()) {
                if (precedence(c) == -1 && c != ' ') { //If current character is not a space or an operator
                    hadOperator = false;
                    ret += c;
                } else if (c != ' ') { //If current character is an operator; take care of spaces as well
                    if (!ret.equals("")) { //add number into queue when operator is read
                        postfix.add(ret);
                        ret = "";
                    }
                    if (hadOperator && c != '(') { //Test to see if character is a possible negative sign
                        if (c != '-') { //If character is not negative it must be a typo (hadOperator being true means that an operator was just read before)
                            System.out.println("Invalid expression");
                            System.exit(0);
                        }
                        ret += "-"; //Treat as number, then continue
                        continue;
                    }
                    if (c != ')')
                        hadOperator = true; //hadOperator has to maintain true if c == ')' since operators can go after parentheses
                    if (c == '(') { //take care of opening parentheses
                        operators.push(c);
                        continue;
                    }
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) //remove operators from stack to postfix expression based on PEMDAS
                        if (!operators.peek().equals('(')) postfix.add(operators.pop() + "");
                        else operators.pop();
                    if (c != ')') operators.push(c); //add current operator to operator stack
                }
            }

            postfix.add(ret); //Adds remaining number
            while (!operators.isEmpty()) postfix.add(operators.pop() + ""); //Adds remaining operators
            calculate = new LinkedStack<>();
            for (String s : postfix) {
                if (s.equals("")) continue;
                if (toChar(s) != 'N') {
                    calculate.push(process(Double.parseDouble(calculate.pop()), Double.parseDouble(calculate.pop()), s) + ""); //pushes to stack new calculated value
                } else calculate.push(s); //push numbers to stack
            }
            System.out.println("Result: " + calculate.pop());
        } catch (Exception e) {
            System.out.println("Invalid Input. Please Try Again."); //Take care of invalid input
        }
    }

    /**
     * YoStack interface containing isEmpty, push, pop, and peek functions.
     *
     * @param <E> element type used.
     */
    interface YoStack<E> {
        boolean isEmpty();

        E push(E e);

        E pop();

        E peek();
    }

    /**
     * LinkedStack class implementing YoStack interface through its requisite functions via a LinkedList object named "stack".
     *
     * @param <E> element type used.
     */
    static class LinkedStack<E> implements YoStack<E> {
        LinkedList<E> stack = new LinkedList<>();

        /**
         * @return true if stack is empty.
         */
        @Override
        public boolean isEmpty() {
            return stack.isEmpty();
        }

        /**
         * @param e to be pushed onto stack.
         * @return element e that was pushed onto stack.
         */
        @Override
        public E push(E e) {
            stack.addFirst(e);
            return e;
        }

        /**
         * Pops element from top of stack.
         * @return popped element.
         */
        @Override
        public E pop() {
            if (stack.isEmpty()) throw new EmptyStackException();
            return stack.removeFirst();
        }

        /**
         * @return element E at top of stack.
         */
        @Override
        public E peek() {
            if (stack.isEmpty()) throw new EmptyStackException();
            return stack.getFirst();
        }
    }
}
