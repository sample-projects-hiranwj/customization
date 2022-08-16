import java.io.*;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("teacher.dep");
        //        File file = new File(System.getProperty("user.home"), "Desktop/employee.dep");

        if(false){
            Teacher visal = new Teacher("T001", new Name("Kasun", "Visal"), "077-1234567");
            if(!file.exists()){file.createNewFile();}
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            System.out.println("Let's write the object");
            oos.writeObject(visal);
            oos.close();
        }else {
            if(!file.exists()){
                System.err.println("No such file exits");return;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            System.out.println("Let's read the object");
            Teacher teacher = (Teacher) ois.readObject();
            System.out.println(teacher);
            ois.close();
        }
    }
}

class Name{
    private String firstName;
    private String lastName;

    public Name() {
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

class Teacher implements Externalizable {
    private String id;
    private transient Name name;
    private String contactNumber;

    public Teacher() {
    }

    public Teacher(String id, Name name, String contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name.getFirstName());
        out.writeObject(name.getLastName());
        out.writeObject(contactNumber);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        String firstName = (String) in.readObject();
        String lastName = (String) in.readObject();
//        name = (String) in.readObject();
        name = new Name(firstName, lastName);
        contactNumber = (String) in.readObject();
    }
}