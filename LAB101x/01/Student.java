public class Student {
    private int id;
    private int semester;
    private int subject;
    private String name;
    private boolean status = true;

    public Student(int a, int b, int c, String d) {
        id = a;
        semester = b;
        subject = c;
        name = d;
    }

    public String getName(){
        return name;
    }

    public int getID() {
        return id;
    }

    public void remove() {
        status = false;
    }

    public boolean getStatus(){
        return status;
    }

    private String getSubject() {
        switch (subject) {
            case 0:
                return "Java";
            case 1:
                return ".Net";
            case 2:
                return "C / C ++";
        }
        return "";
    }

    public String toString() {
        return (name + " | " + getSubject() + " | " + semester);
    }
}