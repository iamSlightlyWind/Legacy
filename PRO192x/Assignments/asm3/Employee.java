public class Employee extends Staff { // extends Staff class which already implemented ICalc
    private int overtime;
    private int baseSalary = 3000000;

    public Employee(int Age, int Factor, int AllowedLeave, int Overtime, String Name, int JoinDate, int deptID,
            String Role, int ID) { // init staff
        this.age = Age;
        this.factor = Factor;
        this.allowedLeave = AllowedLeave;
        this.overtime = Overtime;
        this.name = Name;
        this.department = deptID;
        this.joinDate = JoinDate;
        this.role = Role;
        this.id = ID;
        this.salary = calculateSalary(); // calculate staff salary
    }

    public int calculateSalary() { // calculate staff salary
        return this.factor * this.baseSalary + this.overtime * 200000;
    }

    public void displayInformation() {
        System.out.println("Staff name   : " + this.name);
        System.out.println("Staff age    : " + this.age);
        System.out.println("Staff role   : " + this.role);
        System.out.println("Staff ID     : " + this.id);
        System.out.println("Salary factor: " + this.factor);
        System.out.println("Allowed leave: " + this.allowedLeave);
        System.out.println("Join date    : " + this.joinDate / 1000000 + "/" + (this.joinDate % 1000000) / 10000 + "/"
                + this.joinDate % 10000); // format date
        System.out.println("Department   : " + this.department);
        System.out.println("Salary       : " + this.salary);
    }

    public String getStaff() { // print staff name with role
        return ("(E) " + this.name);
    }
}