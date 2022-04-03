public class Employee extends Staff implements ICalculator {
    private int overtime;
    private int baseSalary = 3000000;

    public Employee(int Age, int Factor, int AllowedLeave, int Overtime, String Name, int JoinDate) {
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

    }
}