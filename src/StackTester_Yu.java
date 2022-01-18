/**
 * Program containing a stack implemented by a LinkedList within the LinkedStack class that also implements a YoStack interface, as well as tester code.
 *
 * @author Ethan Yu
 */

import java.util.EmptyStackException;
import java.util.LinkedList;

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
class LinkedStack<E> implements YoStack<E> {
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

public class StackTester_Yu {

    public static void main(String[] args) {
        //Tester Code
        YoStack<String> dishes = new LinkedStack<>();
        System.out.println("Is it empty: " + dishes.isEmpty());
        System.out.println("Now pushing H, E, L, L, O");
        dishes.push("H");
        dishes.push("E");
        dishes.push("L");
        dishes.push("L");
        dishes.push("O");

        System.out.println("The top element is: " + dishes.peek());

        while (!dishes.isEmpty()) {
            System.out.println("Popping: " + dishes.pop());
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
        System.out.println("Removing " + dishes.pop());
        System.out.println("Removing " + dishes.pop());
        System.out.println("Now pushing Last");
        dishes.push("Last");
        System.out.println("The top element is: " + dishes.peek());

        while (!dishes.isEmpty()) {
            System.out.println("Popping: " + dishes.pop());
        }
    }


}
