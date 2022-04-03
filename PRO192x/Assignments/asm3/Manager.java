public class Manager extends Staff { // extends Staff class which already implemented ICalc
    private int baseSalary = 5000000;

    public Manager(int Age, int Factor, int AllowedLeave, String Name, int JoinDate, int deptID, String Role, int ID) { // init
                                                                                                                        // staff
        this.age = Age;
        this.factor = Factor;
        this.allowedLeave = AllowedLeave;
        this.name = Name;
        this.joinDate = JoinDate;
        this.department = deptID;
        this.role = Role;
        this.id = ID;
        this.salary = calculateSalary(); // calculate staff salary
    }

    public int calculateSalary() { // calculate staff salary
        int responsibility_salary = 0;
        switch (this.department) { // additional salary for responsibility
            case 0: // Business Leader
                responsibility_salary = 8000000;
                break;

            case 1: // Project Leader
                responsibility_salary = 5000000;
                break;

            case 2: // Technical Leader
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
        System.out.println("Join date     : " + this.joinDate / 1000000 + "/" + (this.joinDate % 1000000) / 10000 + "/"
                + this.joinDate % 10000); // format date
        System.out.println("Department    : " + this.deptName[this.department]);
        System.out.println("Salary        : " + this.salary);
    }

    public String getStaff() { // print staff name with role
        return ("(M) " + this.name);
    }
}