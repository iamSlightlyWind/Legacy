public class Employee extends Staff{
    private int overtime;
    private int baseSalary = 3000000;

    public Employee(int Age, int Factor, int AllowedLeave, int Overtime, String Name, int JoinDate, int deptID,
            String Role, int ID) {
        this.age = Age;
        this.factor = Factor;
        this.allowedLeave = AllowedLeave;
        this.overtime = Overtime;
        this.name = Name;
        this.department = deptID;
        this.joinDate = JoinDate;
        this.role = Role;
        this.id = ID;
    }

    public int calculateSalary() {
        return this.factor * this.baseSalary + this.overtime * 200000;
    }

    public void displayInformation() {
        System.out.println("Staff name   : " + this.name);
        System.out.println("Staff age    : " + this.age);
        System.out.println("Staff role   : " + this.role);
        System.out.println("Staff ID     : " + this.id);
        System.out.println("Salary factor: " + this.factor);
        System.out.println("Allowed leave: " + this.allowedLeave);
        System.out.println("Join date    : " + this.joinDate/10000 + "/" + (this.joinDate%10000)/100 + "/" + this.joinDate%100);
        System.out.println("Join date    : " + this.joinDate);
        System.out.println("Department   : " + this.department);
        System.out.println("Salary       : " + calculateSalary());
    }

    public String getStaff() {
        return ("(E) " + this.name);
    }

    public String getStaffName(){
        return (this.name);
    }

    public String getRole() {
        return this.role;
    }

    public int getDept() {
        return this.department;
    }

    public int getID() {
        return this.id;
    }
}