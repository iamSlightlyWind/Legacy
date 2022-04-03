import java.util.ArrayList;
import java.util.Scanner;

public class HumanResoucres {
    static Scanner scan = new Scanner(System.in); // class-wide scanner

    static ArrayList<Staff> staff = new ArrayList<Staff>(); // create staff ArrayList
    static ArrayList<Department> dept = new ArrayList<Department>(); // create dept Arraylist

    public static int inRange(int control, int starting, int ending) { // making sure that variables stay within limit
        for (;;) {
            if (starting > ending) { // variable can be as big as possible
                if (control >= starting)
                    break;
                System.out.println("Value must be equals to or larger than " + (int) starting);
                ending = Integer.MAX_VALUE;
            } else if (starting == ending) { // variable can only be a certain value
                if (control == ending)
                    break;
                System.out.println("Value must be equal to " + starting);
            } else if (ending < starting || control > ending || control < starting) { // variable out of bound
                System.out.println("Value must be in range of " + (int) starting + " to " + (int) ending + ".");
            } else
                break;

            System.out.print("Re-enter value: ");
            control = scan.nextInt(); // force user to enter new value
        }
        return control;
    }

    public static void printOptions(int printChoice) { // handle all user interactions
        System.out.println("\n");
        int choice = 0;
        switch (printChoice) {
            case 1: // default menu
                System.out.println("1. Add an employee");
                System.out.println("2. Add a manager");
                System.out.println("3. Database");
                System.out.println("0. Exit");
                choice = 10; // navigate to menu choices 1x
                break;

            case 3:
                System.out.println("1. List all departments");
                System.out.println("2. List all staffs");
                System.out.println("3. List all staffs by department");

                System.out.println("4. Find staff");
                System.out.println("5. Show payroll");
                choice = 20; // navigate to menu choices 2x
                break;

            case 11: // choice 1.1: Add an employee
                System.out.println("\nAdding an employee..");
                addStaff(1);
                printOptions(1);
                break;

            case 12: // choice 1.2: Add a manager
                System.out.println("\nAdding an manager..");
                addStaff(2);
                printOptions(1);
                break;

            case 13: // choice 1.3: Database menu
                printOptions(3);
                break;

            case 21: // choice 2.1: List all departments
                listDepts();
                printOptions(1);
                break;

            case 22: // choice 2.2: List all staffs
                listStaffs();
                printOptions(1);
                break;

            case 23: // choice 2.3: List all staffs by department
                listStaffsByDept();
                printOptions(1);
                break;

            case 24: // choice 2.4: Search menu
                System.out.println("1. By ID");
                System.out.println("2. By full name");
                choice = 30; // navigate to menu choices 3x
                break;

            case 25: // choice 2.5: print payroll
                printPayroll();
                printOptions(1);
                break;

            case 31: // choice 3.1: search by ID
                searchStaff(1);
                printOptions(1);
                break;

            case 32: // choice 3.1: search by full name
                searchStaff(2);
                printOptions(1);
                break;

            case 10:
                return; // exit
        }

        System.out.print("\nChoice: ");
        if(choice == 20){
            choice += inRange(scan.nextInt(),1,5);
        }else choice += inRange(scan.nextInt(),0,3);
        printOptions(choice); // re-run menu with changed choice
    }

    public static void searchStaff(int type) {
        scan.nextLine(); // consume the last line to prevent scanner from exiting

        switch (type) {
            case 1:
                System.out.print("Enter staff ID to search: ");
                int id = scan.nextInt();
                for (int i = 0; i < staff.size(); i++) { // go through staff list
                    if (staff.get(i).getID() == id) { // check if ID matches
                        System.out.println("Staff found!\n");
                        staff.get(i).displayInformation(); // print staff infos
                        return;
                    }
                }
                break;

            case 2:
                System.out.print("Enter staff ID to search: ");
                String name = scan.nextLine();
                for (int i = 0; i < staff.size(); i++) { // go through staff list
                    if (staff.get(i).getStaffName().equals(name)) { // check if name matches
                        System.out.println("Staff found!\n");
                        staff.get(i).displayInformation(); // print staff infos
                        return;
                    }
                }
                scan.nextLine();
                break;
        }
        System.out.println("Staff not found!"); // only reaches if staff is not found
    }

    public static void printPayroll() { // print pay roll
        System.out.println("Printing payroll..");
        for (int i = 0; i < staff.size(); i++) { // go through staff list
            System.out.println(
                    "ID: " + staff.get(i).getID() + " " + staff.get(i).getStaff() + ": "
                            + staff.get(i).calculateSalary() + " VND"); // print salary with relevant infos
        }
    }

    public static void addStaff(int role) { // add staff
        scan.nextLine();// consume the last line to prevent scanner from exiting

        System.out.print("Enter staff name: "); // get staff infos
        String name = scan.nextLine();

        System.out.print("Enter staff age: ");
        int age = scan.nextInt();

        System.out.print("Enter join date (DDMMYYYY): ");
        int date = scan.nextInt();

        System.out.print("Enter salary factor: ");
        int factor = scan.nextInt();

        System.out.print("Enter allowed leaves: ");
        int allowedLeave = scan.nextInt();

        System.out.println("Available departments: \n1. Business (Sales)\n2. Project (Strategic)\n3. Technical (R&D)");
        System.out.print("Assign department: ");
        int deptID = scan.nextInt() - 1;

        switch (role) { // get relevant infos for different staff roles
            case 1:
                System.out.print("Enter overtime: ");
                int overtime = scan.nextInt();
                staff.add(new Employee(age, factor, allowedLeave, overtime, name, date, deptID, "Employee",
                        staff.size())); // add staff to array
                break;

            case 2:
                staff.add(new Manager(age, factor, allowedLeave, name, date, deptID, "Manager", staff.size())); // add
                                                                                                                // staff
                                                                                                                // to
                                                                                                                // array
                break;
        }
        updateDeptCount();
        scan.nextLine();
    }

    public static void updateDeptCount() { // update dept staff count
        for (int i = 0; i < dept.size(); i++) { // go through every depts
            int count = 0;
            for (int x = 0; x < staff.size(); x++) { // go through staff list
                if (staff.get(x).getDept() == dept.get(i).getDeptID()) { // if staff dept matches deptID
                    count++; // count
                }
            }
            dept.get(i).setCount(count); // update count
        }
    }

    public static void listDepts() { // list depts
        System.out.println("\nListing all departments..");
        for (int i = 0; i < dept.size(); i++) { // go through dept list
            System.out.println();
            dept.get(i).toSring(); // get depts relevant infos
        }
    }

    public static void listStaffs() { // list all staffs
        System.out.println("\nListing all staffs by ID.."); // go through staff list
        for (int i = 0; i < staff.size(); i++) {
            System.out.println();
            staff.get(i).displayInformation(); // print staff infos
        }
    }

    public static void listStaffsByDept() { // list staffs by dept
        System.out.println("\nListing all staffs by department..");
        for (int i = 0; i < dept.size(); i++) { // go through dept list
            System.out.println((i + 1) + ". " + dept.get(i).getDeptName());
            int count = 1;
            for (int x = 0; x < staff.size(); x++) {
                if (staff.get(x).getDept() == dept.get(i).getDeptID()) { // compare deptID with staff deptID
                    System.out.println("  " + count++ + ". " + staff.get(x).getStaff()); // then print staff info
                }
            }
        }
    }

    public static void main(String[] args) { // main func
        System.out.println("Human Resource 0.1A");

        dept.add(new Department(0, "Sales")); // Sales dept matches with Business Leader
        dept.add(new Department(1, "Strategic")); // Strategic dept matches with Project Leader
        dept.add(new Department(2, "R&D")); // rnd dept matches with Technical Leader

        // test data if needed
        // staff.add(new Employee(18, 23, 12, 12, "Wind", 12101994, 0, "Employee",
        // staff.size()));
        // staff.add(new Manager(18, 23, 12, "Ben", 22042009, 0, "Manager",
        // staff.size()));
        // staff.add(new Employee(18, 23, 12, 12, "Erik", 23041998, 1, "Employee",
        // staff.size()));
        // staff.add(new Manager(18, 23, 12, "M K", 29022004, 2, "Manager",
        // staff.size()));
        // staff.add(new Employee(18, 23, 12, 12, "Nick", 27031998, 2, "Employee",
        // staff.size()));
        // staff.add(new Manager(18, 23, 12, "A D S", 21012004, 1, "Manager",
        // staff.size()));

        printOptions(1); // Menu initial
    }
}