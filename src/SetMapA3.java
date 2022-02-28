import java.util.LinkedList;
import java.util.*;

/**
 * @author Ethan Yu
 * The program runs well and handles collisions correctly. While initially testing, there were many bugs.
 * I had to create a specialized data type that could handle both Strings and Integers. The logic flow also
 * became extrmely complex since I had to handle both String and Integer operations. Overall, this was a good experience,
 * and everything works as expected.
 */
public class SetMapA3 {

    static Map<String, Variable> symbolTable = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Amogus.susbus();
        while (true) {
            Amogus.interpreter(sc.nextLine().trim());
        }
    }

    static class Variable {
        String value, type;

        Variable(String type, String value) {
            this.type = type;
            this.value = value;
        }
    }

    static class Amogus {

        /**
         * help function
         */
        static void susbus() {
            System.out.println("Welcome to Amogus!");
            System.out.println("There are 4 commands - vent, bus, sus, and susbus");
            System.out.println("vent - to exit the program");
            System.out.println("bus - to create variables, in the format |name type value|");
            System.out.println("example: bus SUSSY String 1");
            System.out.println("please note that only String and int types are supported");
            System.out.println("sus - to evaluate a statement, in the format |sus expression|");
            System.out.println("example: sus ( 9 + 11 ) * 3 + 6");
            System.out.println("please have spaces separating the different parts of the statement");
            System.out.println("susbus - to get help on the commands");
        }

        static void interpreter(String input) throws Exception {
            try {
                for (String line : input.split(";")) {
                    String[] sus = line.split("\\s+"); // split by whitespace
                    switch (sus[0]) {
                        case "vent":
                            System.out.println("you are sus \nGoodbye");
                            System.exit(0);
                        case "bus":
                            symbolTable.put(sus[1], new Variable(sus[2], sus[3])); //assign variable to symbol table
                            break;
                        case "sus":
                            String ret = "";
                            sus[0] = "";
                            if (sus.length == 2) { //in case user wants to print out a singular object
                                if (symbolTable.containsKey(sus[1]))
                                    System.out.println("Result: " + symbolTable.get(sus[1]));
                                else System.out.println("Result: " + sus[1]);
                                break;
                            }
                            int stringOperation = 0; //1 for String, 2 for int
                            for (String sussy : sus) {
                                if (sussy.equals("")) continue;
                                else if (Calculator.precedence(sussy.charAt(0)) != -1) ret += sussy + " "; //if sussy is an operator
                                else if (symbolTable.containsKey(sussy)) { //if sussy is a variable
                                    if (stringOperation == 0) { //if sussy is the first processed object
                                        if (symbolTable.get(sussy).type.equals("int")) stringOperation = 2;
                                        else stringOperation = 1;
                                        ret += symbolTable.get(sussy).value + " ";
                                        continue;
                                    }
                                    if (stringOperation == 1 && symbolTable.get(sussy).type.equals("int")) //if sussy is a string and the first processed object is an int
                                        throw new AmogusException("InvalidOperationException");
                                    else if (stringOperation == 2 && symbolTable.get(sussy).type.equals("String")) //if sussy is an int and the first processed object is a string
                                        throw new AmogusException("InvalidOperationException");
                                    else stringOperation = symbolTable.get(sussy).type.equals("String") ? 1 : 2; //assign stringOperation
                                    ret += symbolTable.get(sussy).value + " ";
                                } else if (stringOperation == 1 && (sussy.charAt(0) < 65 || sussy.charAt(0) > 122)) { //if sussy is not a variable and not a string and we expect a string
                                    throw new AmogusException("Unknown Variable: " + sussy);
                                } else {
                                    ret += sussy + " "; //if sussy is not a variable
                                    if (stringOperation == 0) {
                                        stringOperation = (sussy.charAt(0) < 65 || sussy.charAt(0) > 122) ? 2 : 1;
                                    }
                                }
                            }
                            if (stringOperation == 1) System.out.println("Result: " + Calculator.bus(ret)); //process string operation
                            else if (stringOperation == 2) System.out.println("Result: " + Calculator.sus(ret)); //process int operation
                            break;
                        case "susbus":
                            susbus(); //help statement
                            break;
                        default:
                            throw new AmogusException("UnknownCommandException: " + sus[0]); //if command is not recognized
                    }

                }
            } catch (AmogusException e) {
                throw new AmogusException("wrong use of methods - type \"susbus\" to get help");
            }
        }

        static class AmogusException extends Exception {
            AmogusException(String message) {
                super(message);
                System.out.println(message);
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

        /**
         * processes string operation
         * @param str operation to process
         * @return result
         */
        static String bus(String str) {
            String[] sus = str.split("\\s+");
            String ret = "";
            for (String sussy : sus) {
                if (!sussy.equals("+")) ret += sussy;
            }
            return ret;
        }

        /**
         * processes int operation
         * @param str input
         * @return result
         * @throws Exception if input is not valid
         */
        static String sus(String str) throws Exception {
            Queue<String> postfix = new LinkedList<>();
            Stack<Character> operators = new Stack<>();
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
            Stack<String> calculate = new Stack<>();
            for (String s : postfix) {
                if (s.equals("")) continue;
                if (toChar(s) != 'N') {
                    try {
                        calculate.push(process(Double.parseDouble(calculate.pop()), Double.parseDouble(calculate.pop()), s) + ""); //pushes to stack new calculated value
                    } catch (Exception e) {
                        throw new Amogus.AmogusException("Unknown variable name " + e.getMessage());
                    }
                } else calculate.push(s); //push numbers to stack
            }
            return calculate.pop();
        }
    }
}
