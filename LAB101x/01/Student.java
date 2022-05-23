import javax.swing.Spring;

public class Student {
    private int id;
    private int semester;
    private int subject;
    private String name;

    public Student(int a, int b, int c, String d) {
        id = a;
        semester = b;
        subject = c;
        name = d;
    }

    public int getID(){
        return id;
    }

    private String getSubject(){
        switch(subject){
            case 0: return "Java";
            case 1: return ".Net";
            case 2: return "C / C ++";
        }
        return "";
    }

    public String toString(){
        return (name + " | " + getSubject() + " | " + semester);
    }
}