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

    public void remove() {
        status = false;
    }

    public boolean getStatus() {
        return status;
    }

    public void setString(int choice, String newString) {
        switch (choice) {
            case 1:
                name = newString;
                break;
        }
    }

    public void setInt(int choice, int newInt) {
        switch (choice) {
            case 1:
                subject = newInt;
                break;
            case 2:
                semester = newInt;
                break;
        }
    }

    public String getString(int choice) {
        switch (choice) {
            case 1:
                return name;
            case 2:
                switch (subject) {
                    case 1:
                        return "Java";
                    case 2:
                        return ".Net";
                    case 3:
                        return "C / C ++";
                }
        }
        return "";
    }

    public int getInt(int choice) {
        switch (choice) {
            case 1:
                return id;
            case 2:
                return semester;
        }
        return 0;
    }

    public String toString() {
        return (name + " | " + getString(2) + " | " + semester);
    }
}