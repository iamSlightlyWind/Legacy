public class Manager extends Staff implements ICalculator {
    private int responsibility_salary = 0;
    private int baseSalary = 5000000;

    public Manager(int Age, int Factor, int AllowedLeave, int responsibility_salary, String Name, int JoinDate) {
        this.age = Age;
        this.factor = Factor;
        this.allowedLeave = AllowedLeave;
        this.name = Name;
        this.joinDate = JoinDate;
        assignResponsibility(responsibility_salary);
    }

    public void assignResponsibility(int role) {
        switch (role) {
            case 1:
                this.responsibility_salary = 8000000;
                break;

            case 2:
                this.responsibility_salary = 5000000;
                break;

            case 3:
                this.responsibility_salary = 6000000;
                break;
        }
    }

    public int calculateSalary() {
        return this.factor * this.baseSalary + responsibility_salary;
    }

    public void displayInformation() {
    }

}