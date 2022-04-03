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
            case 1:
                System.out.println("1. Add an employee");
                System.out.println("2. Add a manager");
                System.out.println("3. Database");
                System.out.println("0. Exit");
                choice = 10;
                break;

            case 3:
                System.out.println("1. List all departments");
                System.out.println("2. List all staffs");
                System.out.println("3. List all staffs by department");

                System.out.println("4. Find staff");
                System.out.println("5. Show payroll");
                choice = 20;
                break;

            case 11:
                System.out.println("\nAdding an employee..");
                addStaff(1);
                printOptions(1);
                break;

            case 12:
                System.out.println("\nAdding an manager..");
                addStaff(2);
                printOptions(1);
                break;

            case 13:
                printOptions(3);
                break;

            case 21:
                listDepts();
                printOptions(1);
                break;

            case 22:
                listStaffs();
                printOptions(1);
                break;

            case 23:
                listStaffsByDept();
                printOptions(1);
                break;

            case 24:
                System.out.println("1. By ID");
                System.out.println("2. By full name");
                choice = 30;
                break;

            case 25:
                break;

            case 31:
                searchStaff(1);
                printOptions(1);
                return;

            case 32:
                searchStaff(2);
                printOptions(1);
                return;
        }

        System.out.print("\nChoice: ");
        choice += scan.nextInt();
        printOptions(choice);
    }

    public static void searchStaff(int type) {
        scan.nextLine();

        switch (type) {
            case 1:
                System.out.print("Enter staff ID to search: ");
                int id = scan.nextInt();
                for (int i = 0; i < staff.size(); i++) {
                    if (staff.get(i).getID() == id) {
                        System.out.println("Staff found!\n");
                        staff.get(i).displayInformation();
                        return;
                    }
                }
                break;

            case 2:
                System.out.print("Enter staff ID to search: ");
                String name = scan.nextLine();
                for (int i = 0; i < staff.size(); i++) {
                    if (staff.get(i).getStaffName().equals(name)) {
                        System.out.println("Staff found!\n");
                        staff.get(i).displayInformation();
                        return;
                    }
                }
                scan.nextLine();
                break;
        }
        System.out.println("Staff not found!");
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
                staff.add(new Employee(age, factor, allowedLeave, overtime, name, date, deptID, "Employee",
                        staff.size()));
                break;

            case 2:
                staff.add(new Manager(age, factor, allowedLeave, name, date, deptID, "Manager", staff.size()));
                break;
        }
        scan.nextLine();
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
            System.out.println("ID: " + i + " " + staff.get(i).getStaff());
        }
    }

    public static void listStaffsByDept() {
        System.out.println("\nListing all staffs by department..");
        for (int i = 0; i < dept.size(); i++) {
            System.out.println((i + 1) + ". " + dept.get(i).getDeptName());
            int count = 1;
            for (int x = 0; x < staff.size(); x++) {
                if (staff.get(x).getDept() == dept.get(i).getDeptID()) {
                    System.out.println("  " + count++ + ". " + staff.get(x).getStaff());
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Human Resource 0.1A");

        dept.add(new Department(0, "Sales"));
        dept.add(new Department(1, "Strategic"));
        dept.add(new Department(2, "R&D"));

        staff.add(new Employee(18, 23, 12, 12, "Wind", 12, 0, "Employee", staff.size()));
        staff.add(new Manager(18, 23, 12, "Ben", 12, 0, "Manager", staff.size()));
        staff.add(new Employee(18, 23, 12, 12, "Erik", 12, 1, "Employee", staff.size()));
        staff.add(new Manager(18, 23, 12, "M K", 12, 2, "Manager", staff.size()));
        staff.add(new Employee(18, 23, 12, 12, "Nick", 12, 2, "Employee", staff.size()));
        staff.add(new Manager(18, 23, 12, "A D S", 12, 1, "Manager", staff.size()));

        printOptions(1);
        System.out.println();
    }
}