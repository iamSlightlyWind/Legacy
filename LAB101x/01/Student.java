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

    public void setString(String choice, String newString) {
        switch (choice) {
            case "name":
                name = newString;
                break;
        }
    }

    public void setInt(String choice, int newInt) {
        switch (choice) {
            case "subject":
                subject = newInt;
                break;
            case "semester":
                semester = newInt;
                break;
        }
    }

    public String getString(String choice) {
        switch (choice) {
            case "name":
                return name;
            case "subject":
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

    public int getInt(String choice) {
        switch (choice) {
            case "id":
                return id;
            case "semester":
                return semester;
            case "subject":
                return subject;
        }
        return 0;
    }

    public String toString() {
        return (name + " | " + getString("subject") + " | " + semester);
    }
}