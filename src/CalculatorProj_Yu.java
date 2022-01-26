import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CalculatorProj_Yu {

    static double ans;
    static boolean assigned = false;

    static int precedence(char operation) {
        if (operation == '+' || operation == '-') {
            return 1;
        } else if (operation == '*' || operation == '/') {
            return 2;
        } else if (operation == '^') {
            return 3;
        } else if (operation == ')') {
            return 0;
        } else return -1;
    }

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

    static void process(int a, String operation) {
        switch (operation) {
            case "+" -> ans += a;
            case "-" -> ans -= a;
            case "*" -> ans *= a;
            case "/" -> ans /= a;
            case "^" -> ans = Math.pow(ans, a);
            default -> {
            }
        }
        ;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Queue<String> postfix = new LinkedList<>();
        YoStack<Character> operators = new LinkedStack<>();
        String ret = "";
        boolean hadOperator = false;
        for (char c : str.toCharArray()) {
            if (precedence(c) == -1 && c != ' ') {
                ret += c;
            } else if (c != ' ') {
                if (!ret.equals("")) {
                    postfix.add(ret);
                    ret = "";
                }
                if (hadOperator) {
                    if (c != '-') {
                        System.out.println("Invalid expression");
                        System.exit(0);
                    }
                    ret += "-";
                    continue;
                }
                hadOperator = true;
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c))
                    postfix.add(operators.pop() + "");
                if (c == ')') operators.pop();
                else operators.push(c);
            }
        }

        while (!operators.isEmpty()) postfix.add(operators.pop() + "");
        YoStack<String> calculate = new LinkedStack<>();
        for (String s : postfix) {
            if (toChar(s) != 'N') {
                if (!assigned) {
                    String temp = calculate.pop();
                    ans = Integer.parseInt(calculate.pop());
                    calculate.push(temp);
                    assigned = true;
                }
                process(Integer.parseInt(calculate.pop()), s);
            } else calculate.push(s);
        }

        System.out.println(ans);

    }
}
