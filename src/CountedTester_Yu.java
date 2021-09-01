import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ethan Yu
 * Assignment: Intro-A3: Counted One Prog
 */


/**
 * Count class that keeps track of number of instances.
 */


class Counted {
    static private int count = 0;

    Counted() {
        count++;
    }

    static void printCount() {
        System.out.println(count);
    }
}

/**
 * Singleton class that only has one instance.
 */


class Singleton {
    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}

/**
 * Contains message dialog containing args and image.
 * Also has test code for Counted and Singleton classes.
 */


public class CountedTester_Yu {
    public static void main(String[] args) throws MalformedURLException {
        JOptionPane.showMessageDialog(null, args[0], "Title", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
                new URL("https://code.bcp.org/anc/bellarmine-college-preparatory-logo.jpg")));
        Counted counted1 = new Counted();
        Counted counted2 = new Counted();
        Counted.printCount();
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton1);
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton2);
    }
}
