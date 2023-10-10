public class Student {
    public String name;
    public double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public String toString() {
        return "(" + name + ", " + gpa + ")";
    }
}