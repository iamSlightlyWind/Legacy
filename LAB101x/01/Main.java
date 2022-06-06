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

        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                createStudentProfile();
                break;
            case 2:
                studentSearchByName();
                break;
            case 3:
                editStudentProfile();
                break;
            case 4:
                break; // waiting
            case 5:
                return;
        }
    }

    public static void createStudentProfile() {
        if (student.size() > 10) {
            System.out.print("Bạn có muốn học tiếp (Y/N) không? ");
            switch (scan.next()) {
                case "Y": break;
                case "N": return;
            }
        }
        System.out.println("---------------------");
        System.out.println("Tạo hồ sơ học sinh: ");

        System.out.print("Tên hhọc sinh: ");
        String name = scan.nextLine();

        System.out.print("Học kỳ: ");
        int semester = scan.nextInt();

        System.out.println("Danh sách môn học: ");
        System.out.println("1. Java\n2. .Net\n3. C / C ++");
        System.out.print("Môn học đăng ký: ");
        int subject = scan.nextInt();

        student.add(new Student(student.size(), semester, subject, name));

        System.out.println("Hồ sơ học sinh được lưu: " + student.get(student.size() - 1).toString());
    }

    public static void studentSearchByName() {
        System.out.println("---------------------");
        System.out.println("Tìm kiếm hồ sơ học sinh: ");

        ArrayList<Student> temp = new ArrayList<Student>();
        for (int i = 0; i < student.size(); i++) {
            temp.add(new Student(student.get(i).getInt("id"), student.get(i).getInt("semester"),
                    student.get(i).getInt("subject"), student.get(i).getString("name")));
        }

        temp.sort((o1, o2) -> o1.getString("name").compareTo(o2.getString("name")));

        System.out.print("Tìm kiếm học sinh bằng tên: ");
        String name = scan.nextLine();

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getStatus()) {
                if (temp.get(i).getString("name").contains(name)) {
                    System.out.println("Tìm thấy học sinh: " + temp.get(i).toString());
                }
            }
        }
    }

    public static int studentSearchByID() {
        System.out.print("ID học sinh: ");
        int id = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getStatus()) {
                if (student.get(i).getInt("id") == id) {
                    System.out.println("Tìm thấy học sinh: " + student.get(i).toString());
                    return i;
                }
            }
        }
        return -1;
    }

    public static void editStudentProfile() {
        System.out.println("---------------------");
        System.out.println("Chỉnh sửa hồ sơ học sinh: ");

        int i = studentSearchByID();
        if (i == -1) {
            System.out.println("Không tìm thấy học viên!");
            return;
        }

        System.out.print("Bạn muốn cập nhật (U) hay xóa (D) học sinh: ");

        switch (scan.nextLine()) {
            case "U":
                System.out.println("Nhập để sửa, bỏ trống để giữ nguyên thông tin học sinh:");
                System.out.print("Tên học sinh (" + student.get(i).getString("name") + "): ");
                String newName = scan.nextLine();
                if (!newName.equals(""))
                    student.get(i).setString("name", newName);

                System.out.println("Danh sách môn học: ");
                System.out.println("1. Java\n2. .Net\n3. C / C ++");
                System.out.print("Môn học đăng ký (" + student.get(i).getString("subject") + "): ");
                int newSubject = scan.nextInt();
                if (!(newSubject == student.get(i).getInt("subject")))
                    student.get(i).setInt("subject", newSubject);

                System.out.print("Học kỳ (" + student.get(i).getInt("semester") + "): ");
                int newSemester = scan.nextInt();
                if (!(newSemester == student.get(i).getInt("semester")))
                    student.get(i).setInt("semester", newSemester);

                break;
            case "D":
                student.get(i).remove();
                break;
        }
    }

    public static void main(String[] args) {
        student.add(new Student(student.size(), 1, 1, "Erik K"));
        student.add(new Student(student.size(), 1, 3, "Erik A"));
        student.add(new Student(student.size(), 1, 2, "Nicky V"));
        student.add(new Student(student.size(), 1, 3, "Kate B"));
        student.add(new Student(student.size(), 1, 1, "Wind Slightly"));
        student.add(new Student(student.size(), 1, 3, "Candace"));
        student.add(new Student(student.size(), 1, 2, "Ferb"));

        studentSearchByName();

        System.out.println();
    }
}