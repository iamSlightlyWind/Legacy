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
                break;

            case 3:
                System.out.println("1. List all departments");
                System.out.println("2. List all staff");
                System.out.println("3. List all managers");
                System.out.println("4. List all employees");
                System.out.println("5. Find employee by ID");
                System.out.println("6. Find manager by ID");
                System.out.println("7. Show payroll");
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
                break;

            case 12:
                break;

            case 13:
                break;

            case 14:
                break;

            case 15:
                break;

            case 16:
                break;

            case 17:
                break;
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

        switch (role) {
            case 1:
                System.out.print("Enter overtime: ");
                int overtime = scan.nextInt();
                staff.add(new Employee(age, factor, allowedLeave, overtime, name, date));
                scan.nextLine();
                break;

            case 2:
                System.out.println(
                        "Available responsibilities: \n1. Business Leader\n2. Project Leader\n3. Technical Leader");
                System.out.print("Assign responsibility: ");
                int additional_responsibility = scan.nextInt();
                staff.add(new Manager(age, factor, allowedLeave, additional_responsibility, name, date));
                scan.nextLine();
                break;
        }
    }

    public static void main(String[] args) {
        System.out.println("Human Resource 0.1A");

        dept.add(new Department(0,"Production"));
        dept.add(new Department(1,"Sales"));
        dept.add(new Department(2,"R&D"));

        System.out.println();
        printOptions(0);
    }
}