import java.util.Scanner;

public class CalculatorProj_Yu {

    int precedence(char operation) {
        if (operation == '+' || operation == '-') {
            return 1;
        } else if (operation == '*' || operation == '/') {
            return 2;
        } else if (operation == '^') {
            return 3;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String postfix = "";
        YoStack<Character> operators = new LinkedStack<>();


    }
}
