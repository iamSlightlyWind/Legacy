package fa.training.entities;

import fa.training.utils.InputValidate;

public class Teacher extends Person {
    private double basicSalary;
    private double subsidy;

    public String[][] info() {
        String superInfo[][] = super.info();

        String values[][] = {
                { "basicSalary", this.getBasicSalary() + "", "double" },
                { "subsidy", this.getSubsidy() + "", "double" },
                { "type", "Teacher", "string" }
        };

        String result[][] = new String[superInfo.length + values.length][3];
        System.arraycopy(superInfo, 0, result, 0, superInfo.length);
        System.arraycopy(values, 0, result, superInfo.length, values.length);

        return result;
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
