import java.util.ArrayList;
import java.util.Scanner;

public class HumanResoucres {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Staff> staff = new ArrayList<Staff>();

    static ArrayList<Department> dept = new ArrayList<Department>();

    public static void printOptions(int printChoice) {
        System.out.println("\n");
        int choice = 0;
        switch (printChoice) {
            case 0:
                System.out.println("1. Add an employee");
                System.out.println("2. Add a manager");
                System.out.println("3. Database");
                System.out.println("0. Exit");
                break;

            case 3:
                System.out.println("1. List all departments");
                System.out.println("2. List all staffs");
                System.out.println("3. List all staffs by department");;
                System.out.println("4. Find employee by ID");
                System.out.println("5. Find manager by ID");
                System.out.println("6. Show payroll");
                choice = 10;
                break;
        }

        System.out.print("\nChoice: ");
        choice += scan.nextInt();

        switch (choice) {
            case 1:
                System.out.println("\nAdding an employee..");
                addStaff(1);
                break;

            case 2:
                System.out.println("\nAdding an manager..");
                addStaff(2);
                break;

            case 3:
                printOptions(3);
                break;

            case 11:
                listDepts();
                break;

            case 12:
                listStaffs();
                break;

            case 13: listStaffsByDept();
                break;

            case 14:
                break;

            case 15:
                break;

            case 16:
                break;

            case 0:
                return;
        }
        printOptions(0);
    }

    public static void addStaff(int role) {
        scan.nextLine();

        System.out.print("Enter staff name: ");
        String name = scan.nextLine();

        System.out.print("Enter staff age: ");
        int age = scan.nextInt();

        System.out.print("Enter join date (DDMMYY): ");
        int date = scan.nextInt();

        System.out.print("Enter salary factor: ");
        int factor = scan.nextInt();

        System.out.print("Enter allowed leaves: ");
        int allowedLeave = scan.nextInt();

        System.out.println("Available departments: \n1. Business (Sales)\n2. Project (Strategic)\n3. Technical (R&D)");
        System.out.print("Assign department: ");
        int deptID = scan.nextInt();

        switch (role) {
            case 1:
                System.out.print("Enter overtime: ");
                int overtime = scan.nextInt();
                staff.add(new Employee(age, factor, allowedLeave, overtime, name, date, deptID, "Employee"));
                scan.nextLine();
                break;

            case 2:
                staff.add(new Manager(age, factor, allowedLeave, name, date, deptID, "Manager"));
                scan.nextLine();
                break;
        }
    }

    public static void listDepts() {
        System.out.println("\nListing all departments..");
        for (int i = 0; i < dept.size(); i++) {
            System.out.println((i + 1) + ". " + dept.get(i).getDeptName());
        }
    }

    public static void listStaffs() {
        System.out.println("\nListing all staffs by ID..");
        for (int i = 0; i < staff.size(); i++) {
            System.out.println((i + 1) + ". " + staff.get(i).getStaff());
        }
    }

    public static void listStaffsByDept() {
        System.out.println("\nListing all staffs by department..");
        for (int i = 0; i < dept.size(); i++) {
            System.out.println((i + 1) + ". " + dept.get(i).getDeptName());
            int count = 1;
            for(int x = 0; x < staff.size(); x++){
                if(staff.get(x).getDept() == dept.get(i).getDeptID()){
                    System.out.println("  " + count++ + ". " + staff.get(x).getStaff());
                }
            }
        }
    }

//    public static void listEmployees() {
//        System.out.println("\nListing all employees..");
//        int count = 1;
//        for (int i = 0; i < staff.size(); i++) {
//            if (staff.get(i).getRole().equals("Employee")) {
//                System.out.println(count + ". " + staff.get(i).getStaff());
//            }
//        }
//    }
//
//    public static void listManagers() {
//        System.out.println("\nListing all managers..");
//        int count = 1;
//        for (int i = 0; i < staff.size(); i++) {
//            if (staff.get(i).getRole().equals("Managers")) {
//                System.out.println(count + ". " + staff.get(i).getStaff());
//            }
//        }
//    }

    public static void main(String[] args) {
        System.out.println("Human Resource 0.1A");

        dept.add(new Department(0, "Sales"));
        dept.add(new Department(1, "Strategic"));
        dept.add(new Department(2, "R&D"));

        staff.add(new Employee(18, 23, 12, 12, "e1", 12, 0, "Employee"));
        staff.add(new Employee(18, 23, 12, 12, "e2", 12, 1, "Employee"));
        staff.add(new Employee(18, 23, 12, 12, "e3", 12, 2, "Employee"));
        staff.add(new Manager( 18, 23, 12,     "m1", 12, 0, "Manager"));
        staff.add(new Manager( 18, 23, 12,     "m2", 12, 2, "Manager"));
        staff.add(new Manager( 18, 23, 12,     "m3", 12, 1, "Manager"));

        printOptions(0);
        System.out.println();
    }
}