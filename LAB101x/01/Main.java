import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Student> student = new ArrayList<Student>();

    public static void printMenu() {
        System.out.println("----------CHÀO MỪNG ĐẾN QUẢN LÝ SINH VIÊN----------");
        System.out.println("1. Tạo");
        System.out.println("2. Tìm kiếm và Sắp xếp");
        System.out.println("3. Cập nhật/Xóa");
        System.out.println("4. Báo cáo");
        System.out.println("5. Thoát");
        System.out.print(
                "\n(Hãy chọn 1 để Tạo, 2 để Tìm kiếm và Sắp xếp, 3 để Cập nhật/Xóa, 4 để Báo cáo, và 5 để Thoát chương trình): ");

        int choice = scan.nextInt();
        scan.nextLine();
        System.out.println();

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
                report();
                break;
            case 5:
                System.exit(0);
                return;
        }
        System.out.println("\n");
    }

    public static void report() {
        if (student.size() == 0) {
            System.out.println("Không có hồ sơ sinh viên trong hệ thống");
        } else {
            System.out.println("----------Danh sách sinh viên----------");
            student.forEach(n -> n.printInfo());
        }
    }

    public static void createStudentProfile() {

        int id = -1;
        System.out.println("----------Tạo hồ sơ sinh viên----------");

        while (id == -1) {
            System.out.print("Nhập ID sinh viên: ");
            id = scan.nextInt();

            for (int i = 0; i < student.size(); i++) {
                if ((id == student.get(i).getInt("id"))) {
                    System.out.println("Đã có sinh viên với ID vừa nhập! Vui lòng sử dụng ID sinh viên khác");
                    id = -1;
                }
            }
        }

        student.add(new Student(id));

        System.out.print("Bạn có muốn học tiếp (Y/N) không? ");
        switch (scan.next()) {
            case "Y":
                System.out.print("\n\n");
                createStudentProfile();
                break;
            case "N":
                return;
        }
    }

    public static void editStudentProfile() {
        System.out.println("----------Chỉnh sửa hồ sơ sinh viên----------");

        int i = studentSearchByID();
        if (i == -1) {
            System.out.println("Không tìm thấy học viên!");
            return;
        }

        System.out.print("Bạn muốn cập nhật (U) hay xóa (D) sinh viên: ");

        switch (scan.nextLine()) {
            case "U":
                System.out.print("Nhập tên sinh viên để sửa, bỏ trống để giữ nguyên thông tin sinh viên: ");
                String newName = scan.nextLine();
                if (!newName.equals(""))
                    student.get(i).setName(newName);

                System.out.println("Môn học đăng ký theo thứ tự: : ");
                student.get(i).printSubjects();
                System.out.print("Thêm (A), sửa (E) hoặc xóa (D) môn học: ");

                switch (scan.next()) {
                    case "A":
                        student.get(i).newSubject();
                        break;
                    case "E":
                        student.get(i).newSubject();
                        break;
                    case "D":
                        student.get(i).removeSubject();
                        break;
                }

                break;
            case "D":
                student.remove(i);
                break;
        }
    }

    public static void studentSearchByName() {
        System.out.println("---------------------");
        System.out.println("Tìm kiếm hồ sơ sinh viên: ");

        ArrayList<Student> temp = new ArrayList<Student>(); // create new temp student arraylist for sorting

        for (int i = 0; i < student.size(); i++) {
            temp.add(student.get(i));
        }

        temp.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

        System.out.print("Tìm kiếm sinh viên bằng tên: ");
        String name = scan.nextLine();

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getName().contains(name)) {
                System.out.println("Tìm thấy sinh viên: " + temp.get(i).getName());
                temp.get(i).printInfo();
                System.out.println();
            }
        }
    }

    public static int studentSearchByID() {
        System.out.print("ID sinh viên: ");
        int id = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getInt("id") == id) {
                System.out.println("Tìm thấy sinh viên: " + student.get(i).getName());
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        student.add(new Student(12, "Slightly Wind"));
        student.add(new Student(13, "Erik Parker"));
        student.add(new Student(14, "Adam Damn"));
        student.add(new Student(15, "Social Dude"));

        student.get(0).addSubject(1, 2);
        student.get(0).addSubject(2, 3);

        student.get(1).addSubject(1, 3);
        student.get(1).addSubject(2, 2);

        student.get(2).addSubject(3, 1);
        student.get(2).addSubject(2, 1);

        student.get(3).addSubject(3, 3);
        student.get(3).addSubject(1, 2);

        while (true) {
            printMenu();
        }
    }
}