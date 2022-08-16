import java.io.*;

public class Demo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("employee.dep");
//        File file = new File(System.getProperty("user.home"), "Desktop/employee.dep");

        if(false){
            Employee name = new Employee("E001", "Pradeep");
            if(!file.exists()) file.createNewFile();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(name);
            System.out.println("Successfully inserted...");
            oos.close();
        }else if(file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            System.out.println(ois.readObject());
            ois.close();
        }

    }
}

class Employee implements Externalizable {
    private String id;
    private String name;


    public Employee() {System.out.println("Employee()");}

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        name = (String) in.readObject();
    }
}

class MyName extends Name implements Serializable{

}