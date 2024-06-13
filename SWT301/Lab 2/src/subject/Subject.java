package subject;

public class Subject {
    private int subject, semester;

    public int getSubject() {
        return this.subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Subject(int newSubject, int newSemester) {
        this.subject = newSubject;
        this.semester = newSemester;
    }

    public int getInt(String argument) {
        if ("subject".equals(argument)) {
            return this.subject;
        } else if ("semester".equals(argument)) {
            return this.semester;
        } else {
            return 0;
        }
    }

    public String info() {
        String subjectName = subjectList(this.subject);
        return subjectName + " | " + this.semester;
    }

    public String subjectList(int n) {
        String subjectName;
        switch (n) {
            case 1:
                subjectName = "Java";
                break;
            case 2:
                subjectName = ".Net";
                break;
            case 3:
                subjectName = "C / C ++";
                break;
            default:
                subjectName = "";
                break;
        }
        return subjectName;
    }

    public boolean compare(Subject that) {
        boolean same = (that.semester == this.semester && that.subject == this.subject);
        return !same;
    }
}