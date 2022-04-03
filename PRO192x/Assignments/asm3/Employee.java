public class Employee extends Staff implements ICalculator {
    private int overtime;
    private int baseSalary = 3000000;

    public Employee(int Age, int Factor, int AllowedLeave, int Overtime, String Name, int JoinDate, int deptID) {
        this.age = Age;
        this.factor = Factor;
        this.allowedLeave = AllowedLeave;
        this.overtime = Overtime;
        this.name = Name;
        this.joinDate = JoinDate;
    }

    public int calculateSalary() {
        return this.factor * this.baseSalary + this.overtime * 200000;
    }

    public void displayInformation() {
        System.out.println("Staff name   : " + this.name);
        System.out.println("Staff age    : " + this.age);
        System.out.println("Staff role   : " + "Employee");
        System.out.println("Salary factor: " + this.factor);
        System.out.println("Allowed leave: " + this.allowedLeave);
        System.out.println("Overtime     : " + this.overtime);
        System.out.println("Join date    : " + this.joinDate);
        System.out.println("Department   : " + this.department);
        System.out.println("Salary       : " + calculateSalary());
    }

    public String getStaff() {
        return ("(E) " + this.name);
    }
}