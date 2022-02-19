import java.util.*;
import java.util.LinkedList;

public class SetMapA3 {
    static Map<String, Integer> symbolTable = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Amogus.interpreter(sc.nextLine().trim());
        }
    }

    static class Amogus {
        static void interpreter(String input) {
            for (String line : input.split(";")) {
                String[] sus = line.split("\\s+"); // split by whitespace
                switch (sus[0]) {
                    case "vent":
                        System.out.println("you are sus \nGoodbye");
                        System.exit(0);
                    case "bus":
                        symbolTable.put(sus[1], Integer.parseInt(sus[2]));
                        break;
                    case "sus":
                        Calculator.sus(line.substring(line.indexOf(" ") + 1));
                        break;
                }

            }
        }
    }

   static class Calculator {
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

        static String sus(String str) {
            Queue<String> postfix = new LinkedList<>();
            Stack<Character> operators = new Stack<>();
            String ret = "";
            boolean hadOperator = true;
            for (char c : str.toCharArray()) {
                if (precedence(c) == -1 && c != ' ') { //If current character is not a space or an operator
                    hadOperator = false;
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
            Stack<String> calculate = new Stack<>();
            for (String s : postfix) {
                if (s.equals("")) continue;
                if (toChar(s) != 'N') {
                    calculate.push(process(Double.parseDouble(calculate.pop()), Double.parseDouble(calculate.pop()), s) + ""); //pushes to stack new calculated value
                } else calculate.push(s); //push numbers to stack
            }
            return calculate.pop();
        }
    }

}
