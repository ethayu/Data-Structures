import java.time.Year;
import java.util.ArrayList;

/**
 * @author Ethan Yu
 */

abstract class Person {

    static int count = 0;
    private final int year = Year.now().getValue(), id = count++;
    private String fName, lName, greeting;

    /**
     * Constructor for Person class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public Person(String firstName, String lastName, String greeting) {
        System.out.println(getClass().getName() + " " + id + " born");
        setfName(firstName);
        setlName(lastName);
        setGreeting(greeting);
    }

    /**
     * An abstract greet method.
     * @return the greeting as a String.
     */
    abstract public String greet();

    /**
     * Sets firstName.
     * @param fName First Name of person.
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * Sets lastName.
     * @param lName Last name of person.
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * Sets greeting.
     * @param greeting greeting of person.
     */
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    /**
     * gets First Name of person.
     * @return first name of person in String form.
     */
    public String getfName() {
        return fName;
    }

    /**
     * gets last Name of person
     * @return last name of person in String form.
     */
    public String getlName() {
        return lName;
    }

    /**
     * gets greeting of person.
     * @return greeting of person in String form.
     */
    public String getGreeting() {
        return greeting;
    }

    /**
     * toString() method of Person class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "Person{" +
                "year=" + year +
                ", id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", greeting='" + greeting + '\'' +
                ", class='" + getClass().getName() + '\'' +
                '}';
    }
}

class Student extends Person {
    /**
     * Constructor for Student class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public Student(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return getGreeting() + ", I am student " + getfName() + " " + getlName() + " and I am a " + getClass().getName() + ".";
    }
}

class Teacher extends Person {
    /**
     * Constructor for Teacher class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public Teacher(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return getGreeting() + ", I am teacher " + getfName() + " " + getlName() + " and I am a " + getClass().getName() + ".";
    }
}

class BCPStudent extends Student {
    /**
     * Constructor for BCPStudent class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public BCPStudent(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }
}

class BCPTeacher extends Teacher {
    /**
     * Constructor for BCPTeacher class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public BCPTeacher(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }
}

class NDStudent extends Student {
    /**
     * Constructor for NDStudent class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public NDStudent(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }
}

class NDTeacher extends Teacher {
    /**
     * Constructor for NDTeacher class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public NDTeacher(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }
}

public class PersonCreator_EYu {
    public static void main(String[] args) {
        /*
            Tester code.
         */
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Student("Bob", "Jones", "Hello"));
        people.add(new Teacher("Bob", "Jonesy", "Hola"));
        people.add(new BCPStudent("Nell", "Bellar", "Knee-how"));
        people.add(new BCPTeacher("Nell", "Bel-air", "Konichiwa"));
        people.add(new NDStudent("Cindy", "Johnson", "Bonjour"));
        people.add(new NDTeacher("Mark", "Jones", "Guten tag"));
        for (Person person : people) System.out.println(person.greet());
    }
}
