
/*
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PeerReview {
    public static void main(String[] args) throws MalformedURLException {
        Counted c1 = new Counted();
        Counted c2 = new Counted();
        Counted c3 = new Counted();
        System.out.println(c1.getCount());
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        String name = JOptionPane.showInputDialog("What is your favorite food?");
        System.out.println("I don't like " + name + " My favorite food is sushi");
        URL imageLocation = new URL("https://code.bcp.org/anc/bellarmine-college-preparatory-logo.jpg");
        JOptionPane.showMessageDialog(null, "Hello", "Title", JOptionPane.PLAIN_MESSAGE, new ImageIcon(imageLocation));
    }
}

//Class that keeps count of times it is created.
class Counted {
    private static int count;

    //constructor
    public Counted() {
        count++;
    }

    //getter method.
    public int getCount() {
        return count;
    }
}

//Only one instance of this class can exists
class Singleton {
    private static Singleton instance = null;

    //private constructor so can't make more by using new
    private Singleton() {
    }//Static so can be called by Class name directly

    public static Singleton getInstance() {
        //check if an instance of Singleton has been made yet if not
        // create one
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}*/
