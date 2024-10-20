package fa.training.entities;

import fa.training.utils.InputValidate;

public class Teacher extends Person {
    private String teacherID;
    private double basicSalary;
    private double subsidy;

    public String[][] info() {
        String values[][] = {
                { "id", this.getTeacherID(), "string" },
                { "basicSalary", this.getBasicSalary() + "", "double" },
                { "subsidy", this.getSubsidy() + "", "double" },
                { "type", "Teacher", "string" }
        };

        return values;
    }

    public String[][] superInfo() {
        return super.info();
    }

    public Teacher() {
        super();
        this.basicSalary = InputValidate.inputDouble("Enter salary: ",
                "Not a number",
                "Salary must be greater than 0",
                0d,
                null);
        this.subsidy = InputValidate.inputDouble("Enter subsidy: ",
                "Not a number",
                "Subsidy must be greater than 0",
                0d,
                null);
    }

    public Teacher(double basicSalary, double subsidy) {
        this.basicSalary = basicSalary;
        this.subsidy = subsidy;
    }

    public Teacher(Long id, String name, String gender, String phone, String email, String teacherID, double basicSalary, double subsidy) {
        super(id, name, gender, phone, email);
        this.teacherID = teacherID;
        this.basicSalary = basicSalary;
        this.subsidy = subsidy;
    }

    public double calculateSalary() {
        return this.basicSalary + this.subsidy;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }
}
