abstract class Person {
    static int count = 0;
    private final int year = 0, id = count++;
    private String fName, lName, greeting;
    public Person (String firstName, String lastName, String greeting) {
        System.out.println("Person " + id + "  born");
        setfName(firstName);
        setlName(lastName);
        setGreeting(greeting);
    }

    abstract public void greet();

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getGreeting() {
        return greeting;
    }
}

class Student extends Person {
    String greeting = "Hi, I am student ";
    public Student(String firstName, String lastName, String greeting) {
        super(firstName, lastName, greeting);
        greeting += getfName() + " " + getlName() + " and I am a";
    }

    @Override
    public void greet() {
        System.out.println(greeting + " generic Student.");
    }
}

class Teacher extends Person {
    String greeting = "Hi, I am teacher ";
    public Teacher(String firstName, String lastName) {
        super(firstName, lastName, greeting);
        greeting += getfName() + " " + getlName() + " and I am a";
    }

    @Override
    public void greet() {
        System.out.println(greeting + " generic Teacher.");
    }
}

class BCPStudent extends Student {
    String greeting = "Hi, I am student ";
    public BCPStudent(String firstName, String lastName) {
        super(firstName, lastName, greeting);
        greeting += getfName() + " " + getlName() + " and I am a";
    }

    @Override
    public void greet() {
        System.out.println(greeting + " BCP Student.");
    }
}

class BCPTeacher extends Teacher {
    String greeting = "Hi, I am student ";
    public BCPTeacher(String firstName, String lastName) {
        super(firstName, lastName);
        greeting += getfName() + " " + getlName() + " and I am a";
    }

    @Override
    public void greet() {
        System.out.println(greeting + " BCP Teacher.");
    }
}

class NDStudent extends Student {
    String greeting = "Hi, I am student ";
    public NDStudent(String firstName, String lastName) {
        super(firstName, lastName, greeting);
        greeting += getfName() + " " + getlName() + " and I am a";
    }

    @Override
    public void greet() {
        System.out.println(greeting + " ND Student.");
    }
}

class NDTeacher extends Teacher {
    String greeting = "Hi, I am student ";
    public NDTeacher(String firstName, String lastName) {
        super(firstName, lastName);
        greeting += getfName() + " " + getlName() + " and I am a";
    }

    @Override
    public void greet() {
        System.out.println(greeting + " ND Teacher.");
    }
}

public class PersonCreator_EYu {


}