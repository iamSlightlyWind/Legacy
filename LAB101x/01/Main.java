import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Student> student = new ArrayList<Student>();

    public static void printMenu() {
        System.out.println("1. Tạo");
        System.out.println("2. Tìm kiếm và Sắp xếp");
        System.out.println("3. Cập nhật/Xóa");
        System.out.println("4. Báo cáo");
        System.out.println("5. Thoát");
        System.out.println(
                "\n(Hãy chọn 1 để Tạo, 2 để Tìm kiếm và Sắp xếp, 3 để Cập nhật/Xóa, 4 để Báo cáo, và 5 để Thoát chương trình).");
    }

    public static void createStudentProfile() {
        System.out.println("---------------------");
        System.out.println("Creating a student profile");

        System.out.print("Student name: ");
        String name = scan.nextLine();

        System.out.print("Select semester: ");
        int semester = scan.nextInt();

        System.out.println("Subjects list: ");
        System.out.println("1. Java\n2. .Net\n3. C / C ++");
        System.out.print("Chosen subject: ");
        int subject = scan.nextInt();

        student.add(new Student(student.size(), semester, subject, name));

        System.out.println("Student profile saved: " + student.get(student.size() - 1).toString());
    }

    public static void studentSearch() {
        System.out.println("---------------------");
        System.out.println("Searching for a student profile");

        System.out.println("Enter search method:\n1. By name\n2. By ID");
        int method = scan.nextInt();
    }

    public static void getStudent() {

    }

    public static void editStudentProfile() {
        System.out.println("---------------------");
        System.out.println("Creating a student profile");

        System.out.print("Enter student ID: ");
        int id = scan.nextInt();
    }

    public static void main(String[] args) {
        // printMenu();
        // Student Erik = new Student(5, 2, 1, "Erik");
        student.add(new Student(student.size(), 2, 1, "Erik"));
        // System.out.println(Erik.toString());
        createStudentProfile();
        System.out.println();
    }
}