public class Subject {
    private int subject, semester;

    public Subject(int newSubject, int newSemester) {
        subject = newSubject;
        semester = newSemester;
    }

    public int getInt(String argument) {
        switch (argument) {
            case "subject":
                return subject;
            case "semester":
                return semester;
        }
        return 0;
    }

    public String info(){
        return (printSubjectName(subject) + " | " + semester);
    }

    public String printSubjectName(int n) {
        switch (n) {
            case 1:
                return ("Java");
            case 2:
                return (".Net");
            case 3:
                return ("C / C ++");
        }
        return "";
    }

    public boolean compare(Subject that) {
        return !(that.semester == this.semester && that.subject == this.subject);
    }
}
