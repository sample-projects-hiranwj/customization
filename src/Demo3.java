import java.awt.image.BufferedImage;
import java.io.*;

public class Demo3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("student.dep");
//        File file = new File(System.getProperty("user.home"), "Desktop/employee.dep");

        if (false){
            if (!file.exists()) file.createNewFile();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            var fis = new FileInputStream(file);
            var bis = new ByteArrayOutputStream(10);
            try (oos;fis;bis){
                Student student = new Student("S001", "Namal", 80);
                oos.writeObject(student);
            }
        } else if (file.exists()) {
            try(var ois = new ObjectInputStream(new FileInputStream(file))) {
                Student student = (Student) ois.readObject();
                System.out.println(student);
            }
        }
    }
}

enum Grade{
    A, B, C, S, W
}

class People{
    private String id;
    private String name;

    public People() {
    }

    public People(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class Student extends People implements Serializable{
    private int marks;
    private Grade grade;

    public Student(String id, String name, int marks) {
        super(id, name);
        System.out.println("Student(id,name,marks)");
        setMarks(marks);
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        if(marks > 100 || marks < 0) throw new RuntimeException("Invalid marks");
        this.marks = marks;
        if(marks>=75){
            grade = Grade.A;
        } else if (marks>=65) {
            grade = Grade.B;
        } else if (marks>=55) {
            grade = Grade.C;
        } else if (getMarks()>=45) {
            grade = Grade.S;
        } else {
            grade = Grade.W;
        }
    }

    public Grade getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "marks=" + marks +
                ", grade=" + grade +
                '}';
    }
}