package fa.training.entities;

import fa.training.annotation.DatabaseColumn;
import fa.training.annotation.DatabaseTable;
import fa.training.utils.InputValidate;

@DatabaseTable(name = "teacher_table")
public class Teacher extends Person {

    @DatabaseColumn(name = "basic_salary")
    private double basicSalary;

    @DatabaseColumn(name = "subsidy")
    private double subsidy;

    public Teacher() {
    }

    public Teacher(boolean promptFromKeyboard) {
        super(promptFromKeyboard);
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

    public Teacher(Long id, String name, String gender, String phone, String email, double basicSalary, double subsidy) {
        super(id, name, gender, phone, email);
        this.basicSalary = basicSalary;
        this.subsidy = subsidy;
    }

    public double calculateSalary() {
        return this.basicSalary + this.subsidy;
    }

    public double getBasicSalary() {
        return basicSalary;
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
