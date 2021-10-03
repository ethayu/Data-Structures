import java.time.Year;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Ethan Yu
 */

abstract class Person implements Comparable<Person> {

    static int count = 0;
    private final int year = Year.now().getValue(), id = count++;
    private String fName, lName, greeting;
    static int PersonspecificCount = 0, PersonfamilyCount = 0;

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
        if (this.getClass().getName().equals("Person")) PersonspecificCount++;
        PersonfamilyCount++;
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
     * gets id of person.
     * @return id of person in String form.
     */
    public int getId() {
        return id;
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
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
    }

    /**
     * compareTo method of Person class implemented by comparing names.
     * @param o object to be compared to
     * @return positive integer if current object is greater; 0 if equal; negative number if less.
     */
    @Override
    public int compareTo(Person o) {
        if (this instanceof Teacher) {
            if (o instanceof Student) return 1;
            else if (this.lName.compareTo(o.lName) == 0) return this.fName.compareTo(o.fName);
            else return this.lName.compareTo(o.lName);
        } else if (o instanceof Teacher) return -1;
        else if (this.lName.compareTo(o.lName) == 0) return this.fName.compareTo(o.fName);
        else return this.lName.compareTo(o.lName);
    }

    /**
     * equals method that checks if two Person objects are equal by comparing ids.
     * @param o object to be compared to
     * @return boolean if o is equal to current object.
     */
    public boolean equals(Person o) {
        return id == o.id;
    }

    /**
     * @return specificCount of Person class
     */
    static int specificCount() {
        return PersonspecificCount;
    }

    /**
     * @return familyCount of Person class
     */
    static int familyCount() {
        return PersonfamilyCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFQN() {
        return getClass().getName() + "." + hashCode() + "." + getId();
    }

}

class Student extends Person {
    static int StudentspecificCount = 0, StudentfamilyCount = 0;
    /**
     * Constructor for Student class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public Student(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        if (this.getClass().getName().equals("Student")) StudentspecificCount++;
        StudentfamilyCount++;
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return getGreeting() + ", I am student " + getfName() + " " + getlName() + " and I am a " + getClass().getName() + ".";
    }

    /**
     * @return specificCount of Student class
     */
    public static int specificCount() {
        return StudentspecificCount;
    }

    /**
     * @return familyCount of Student class
     */
    public static int familyCount() {
        return StudentfamilyCount;
    }

    /**
     * toString() method of Student class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
    }
}

class Teacher extends Person {
    static int TeacherspecificCount = 0, TeacherfamilyCount = 0;
    /**
     * Constructor for Teacher class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public Teacher(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        if (this.getClass().getName().equals("Teacher")) TeacherspecificCount++;
        TeacherfamilyCount++;
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return getGreeting() + ", I am teacher " + getfName() + " " + getlName() + " and I am a " + getClass().getName() + ".";
    }

    /**
     * @return specificCount of Teacher class
     */
    public static int specificCount() {
        return TeacherspecificCount;
    }

    /**
     * @return familyCount of Teacher class
     */
    public static int familyCount() {
        return TeacherfamilyCount;
    }

    /**
     * toString() method of Teacher class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
    }
}

class BCPStudent extends Student {
    static int BCPStudentspecificCount = 0, BCPStudentfamilyCount = 0;
    /**
     * Constructor for BCPStudent class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public BCPStudent(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        if (this.getClass().getName().equals("BCPStudent")) BCPStudentspecificCount++;
        BCPStudentfamilyCount++;
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }

    /**
     * @return specificCount of BCPStudent class
     */
    public static int specificCount() {
        return BCPStudentspecificCount;
    }

    /**
     * @return familyCount of BCPStudent class
     */
    public static int familyCount() {
        return BCPStudentfamilyCount;
    }
    /**
     * toString() method of BCPStudent class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
    }
}

class BCPTeacher extends Teacher {
    static int BCPTeacherspecificCount = 0, BCPTeacherfamilyCount = 0;
    /**
     * Constructor for BCPTeacher class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public BCPTeacher(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        if (this.getClass().getName().equals("BCPTeacher")) BCPTeacherspecificCount++;
        BCPTeacherfamilyCount++;
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }

    /**
     * @return specificCount of BCPTeacher class
     */
    public static int specificCount() {
        return BCPTeacherspecificCount;
    }

    /**
     * @return familyCount of BCPTeacher class
     */
    public static int familyCount() {
        return BCPTeacherfamilyCount;
    }

    /**
     * toString() method of BCPTeacher class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
    }
}

class NDStudent extends Student {
    static int NDStudentspecificCount = 0, NDStudentfamilyCount = 0;
    /**
     * Constructor for NDStudent class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public NDStudent(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        if (this.getClass().getName().equals("NDStudent")) NDStudentspecificCount++;
        NDStudentfamilyCount++;
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }

    /**
     * @return specificCount of NDStudent class
     */
    public static int specificCount() {
        return NDStudentfamilyCount;
    }

    /**
     * @return familyCount of NDStudent class
     */
    public static int familyCount() {
        return NDStudentfamilyCount;
    }

    /**
     * toString() method of NDStudent class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
    }
}

class NDTeacher extends Teacher {
    static int NDTeacherspecificCount = 0, NDTeacherfamilyCount = 0;
    /**
     * Constructor for NDTeacher class. Assigns values to firstName, lastName, and greeting variables and prints out a statement as well.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param greeting the greeting of the person.
     */
    public NDTeacher(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        if (this.getClass().getName().equals("NDTeacher")) NDTeacherspecificCount++;
        NDTeacherfamilyCount++;
    }

    /**
     * A greet method.
     * @return the greeting as a String.
     */
    @Override
    public String greet() {
        return super.greet();
    }

    /**
     * @return specificCount of NDTeacher class
     */
    public static int specificCount() {
        return NDTeacherspecificCount;
    }

    /**
     * @return familyCount of NDTeacher class
     */
    public static int familyCount() {
        return NDTeacherfamilyCount;
    }

    /**
     * toString() method of NDTeacher class.
     * @return instance variables and class name of object as String.
     */
    @Override
    public String toString() {
        return "id# " + getId() + " of size: " + specificCount() + ": " + getfName() + " " + getlName();
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
        System.out.println(Person.familyCount() + " " + Person.specificCount());
        System.out.println(Student.familyCount() + " " + Student.specificCount());
        System.out.println(Teacher.familyCount() + " " + Teacher.specificCount());
        System.out.println(BCPStudent.familyCount() + " " + BCPStudent.specificCount());
        System.out.println(BCPTeacher.familyCount() + " " + BCPTeacher.specificCount());
        System.out.println(NDStudent.familyCount() + " " + NDStudent.specificCount());
        System.out.println(NDTeacher.familyCount() + " " + NDTeacher.specificCount());
        for (Person person : people) System.out.println(person.getFQN());
    }
}
