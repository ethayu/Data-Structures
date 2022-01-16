
interface YoStack<E> {
    boolean isEmpty();
    E push(E e);
    E pop();
    E peek();
}

class LinkedStack<E> implements YoStack<E> {
    LinkedList stack = new LinkedList();
    public boolean isEmpty() {
        return stack.getFirst() == null;
    }

    public E push(E e) {
        stack.getFirst().n
        return e;
    }

    public E pop() {
        return null;
    }

    public E peek() {
        return null;
    }
}

public class StackTester_Yu {

    public static void main(String[] args) {
        Stack<String> dishes = new Stack<String>();
        System.out.println("Is it empty: " + dishes.isEmpty());
        System.out.println("Now pushing H, E, L, L, O");
        dishes.push("H");
        dishes.push("E");
        dishes.push("L");
        dishes.push("L");
        dishes.push("O");

        System.out.println("The top element is: " + dishes.peek());

        while (!dishes.isEmpty()){
            System.out.println("Popping: "+dishes.pop());
        }
        System.out.println("Is it empty: " + dishes.isEmpty());
        System.out.println("Now pushing 1");
        dishes.push("1");
        System.out.println("Is it empty: " + dishes.isEmpty());
        System.out.println("Now pushing 2, 3, 4, 5");
        dishes.push("2");
        dishes.push("3");
        dishes.push("4");
        dishes.push("5");
        System.out.println("The top element is: " + dishes.peek());
        System.out.println("Removing " + dishes.pop() );
        System.out.println("Removing "+ dishes.pop() );
        System.out.println("Now pushing Last");
        dishes.push("Last");
        System.out.println("The top element is: " + dishes.peek());

        while (!dishes.isEmpty()){
            System.out.println("Popping: " +dishes.pop());
        }
    }


}
