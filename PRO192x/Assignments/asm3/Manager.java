public class Manager extends Staff{
    private int baseSalary = 5000000;

    public Manager(int Age, int Factor, int AllowedLeave, String Name, int JoinDate, int deptID, String Role, int ID) {
        this.age = Age;
        this.factor = Factor;
        this.allowedLeave = AllowedLeave;
        this.name = Name;
        this.joinDate = JoinDate;
        this.department = deptID;
        this.role = Role;
        this.id = ID;
        this.salary = calculateSalary();
    }

    public int calculateSalary() {
        int responsibility_salary = 0;
        switch (this.department) {
            case 1:
                responsibility_salary = 8000000;
                break;

            case 2:
                responsibility_salary = 5000000;
                break;

            case 3:
                responsibility_salary = 6000000;
                break;
        }
        return this.factor * this.baseSalary + responsibility_salary;
    }

    public void displayInformation() {
        System.out.println("Staff name    : " + this.name);
        System.out.println("Staff age     : " + this.age);
        System.out.println("Staff role    : " + this.role);
        System.out.println("Staff ID      : " + this.id);
        System.out.println("Salary factor : " + this.factor);
        System.out.println("Allowed leave : " + this.allowedLeave);
        System.out.println("Join date     : " + this.joinDate/1000000 + "/" + (this.joinDate%1000000)/10000 + "/" + this.joinDate%10000);
        System.out.println("Department    : " + this.department);
        System.out.println("Salary        : " + this.salary);
    }

    public String getStaff() {
        return ("(M) " + this.name);
    }
}