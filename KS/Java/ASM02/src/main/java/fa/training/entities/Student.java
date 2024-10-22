package fa.training.entities;

import fa.training.annotation.DatabaseColumn;
import fa.training.annotation.DatabaseTable;
import fa.training.utils.InputValidate;

@DatabaseTable(name = "student")
public class Student extends Person {

    @DatabaseColumn(name = "studentid")
    private String studentId;

    @DatabaseColumn(name = "theory")
    private Double theory;

    @DatabaseColumn(name = "practice")
    private Double practice;

    public Student(boolean promptFromKeyboard) {
        super(promptFromKeyboard);
        this.studentId = InputValidate.inputString("Enter student ID: ");
        this.theory = InputValidate.inputDouble("Enter theory score:",
                "Not a number",
                "Theory score must be in between 0 and 10",
                0d,
                10d);
        this.practice = InputValidate.inputDouble("Enter practice score:",
                "Not a number",
                "Practice score must be in between 0 and 10",
                0d,
                10d);

    }

    public Student() {
        super();
    }

    public Student(Long id, String name, String gender, String phone, String email, String studentId, Double theory, Double practice) {
        super(id, name, gender, phone, email);
        this.studentId = studentId;
        this.theory = theory;
        this.practice = practice;
    }

    public Student(String studentId, double theory, double practice) {
        this.studentId = studentId;
        this.theory = theory;
        this.practice = practice;
    }

    public double calculateFinalMark() {
        return (practice + theory) / 2;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getTheory() {
        return theory;
    }

    public void setTheory(Double theory) {
        this.theory = theory;
    }

    public Double getPractice() {
        return practice;
    }

    public void setPractice(Double practice) {
        this.practice = practice;
    }

    public void update(Student student) {
        this.setGender(student.getGender() != null && !student.getGender().isEmpty() ? student.getGender() : this.getGender());
        this.setPhone(student.getPhone() != null && !student.getPhone().isEmpty() ? student.getPhone() : this.getPhone());
        this.setEmail(student.getEmail() != null && !student.getEmail().isEmpty() ? student.getEmail() : this.getEmail());
        this.setTheory(student.getTheory() != null ? student.getTheory() : this.getTheory());
        this.setPractice(student.getPractice() != null ? student.getPractice() : this.getPractice());
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", theory=" + theory +
                ", practice=" + practice +
                "} " + super.toString();
    }
}
