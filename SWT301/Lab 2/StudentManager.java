import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {

    ArrayList<Student> student = new ArrayList<Student>();

    public void printMenu() {
        Scanner scan = new Scanner(System.in);
        printWelcomeMessage();

        int choice = scan.nextInt();
        scan.nextLine();
        System.out.println();

        handleMenuChoice(choice);

        System.out.println("\n");
    }

    public void printWelcomeMessage() {
        System.out.println("----------CHÀO MỪNG ĐẾN QUẢN LÝ SINH VIÊN----------");
        System.out.println("1. Tạo");
        System.out.println("2. Tìm kiếm và Sắp xếp");
        System.out.println("3. Cập nhật/Xóa");
        System.out.println("4. Báo cáo");
        System.out.println("5. Thoát");
        System.out.print(
                "\n(Hãy chọn 1 để Tạo, 2 để Tìm kiếm và Sắp xếp, 3 để Cập nhật/Xóa, 4 để Báo cáo, và 5 để Thoát chương trình): ");
    }

    public void handleMenuChoice(int choice) {
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
    }

    public void report() {
        int studentSize = student.size();
        boolean isStudentListEmpty = studentSize == 0;

        if (isStudentListEmpty) {
            System.out.println("Không có hồ sơ sinh viên trong hệ thống");
        } else {
            System.out.println("----------Danh sách sinh viên----------");
            for (int i = 0; i < studentSize; i++) {
                Student currentStudent = student.get(i);
                currentStudent.printInfo();
            }
        }
    }

    public int checkID(int id) {
        boolean isIdNegative = id < 0;
        if (isIdNegative) {
            return 0;
        }

        int studentSize = student.size();
        for (int i = 0; i < studentSize; i++) {
            Student currentStudent = student.get(i);
            int currentId = currentStudent.getInt("id");

            boolean isIdMatch = id == currentId;
            if (isIdMatch) {
                return -1;
            }
        }

        return 1;
    }

    public void createStudentProfile() {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------Tạo hồ sơ sinh viên----------");

        int id;
        int checkResult;

        do {
            System.out.print("Nhập ID sinh viên: ");
            id = scan.nextInt();
            checkResult = checkID(id);

            if (checkResult == 0) {
                System.out.println("ID không hợp lệ! Vui lòng nhập lại.");
            } else if (checkResult == -1) {
                System.out.println("Đã có sinh viên với ID vừa nhập! Vui lòng sử dụng ID sinh viên khác");
            }
        } while (checkResult != 1);

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

    public Student getStudentById(int id) {
        Student foundStudent = null;
        for (Student s : student) {
            int currentId = s.getId();
            if (currentId == id) {
                foundStudent = s;
                break;
            }
        }
        return foundStudent;
    }

    public void editStudentProfile() {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------Chỉnh sửa hồ sơ sinh viên----------");

        int id = studentSearchByID();
        boolean studentNotFound = id == -1;
        if (studentNotFound) {
            System.out.println("Không tìm thấy học viên!");
            return;
        }

        Student currentStudent = getStudentById(id);
        boolean studentIsNull = currentStudent == null;
        if (studentIsNull) {
            System.out.println("Không tìm thấy học viên!");
            return;
        }

        System.out.print("Bạn muốn cập nhật (U) hay xóa (D) sinh viên: ");
        String action = scan.nextLine();

        boolean update = "U".equals(action);
        boolean remove = "D".equals(action);

        if (update) {
            System.out.print("Nhập tên sinh viên để sửa, bỏ trống để giữ nguyên thông tin sinh viên: ");
            String newName = scan.nextLine();
            boolean nameIsNotEmpty = !newName.equals("");
            if (nameIsNotEmpty) {
                currentStudent.setName(newName);
            }

            System.out.println("Môn học đăng ký theo thứ tự: : ");
            currentStudent.printSubjectList();
            System.out.print("Thêm (A), xóa (D) hoặc giữ nguyên (S) môn học : ");

            String subjectAction = scan.next();
            boolean add = "A".equals(subjectAction);
            boolean delete = "D".equals(subjectAction);
            boolean keep = "S".equals(subjectAction);

            if (add) {
                currentStudent.newSubject();
            } else if (delete) {
                currentStudent.removeSubject();
            } else if (keep) {
            }

        } else if (remove) {
            student.remove(currentStudent);
        }
    }

    public ArrayList<Student> sortStudentsByName() {
        ArrayList<Student> temp = new ArrayList<Student>();
        boolean isListEmpty = student.isEmpty();

        if (!isListEmpty) {
            for (int i = 0; i < student.size(); i++) {
                Student currentStudent = student.get(i);
                temp.add(currentStudent);
            }

            Comparator<Student> compareByName = new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            };

            temp.sort(compareByName);
        }

        return temp;
    }

    public ArrayList<Student> searchStudentsByName(String name, ArrayList<Student> students) {
        ArrayList<Student> foundStudents = new ArrayList<Student>();
        boolean isStudentFound = false;

        for (int i = 0; i < students.size(); i++) {
            Student currentStudent = students.get(i);
            String currentStudentName = currentStudent.getName();

            if (currentStudentName.toLowerCase().contains(name.toLowerCase())) {
                foundStudents.add(currentStudent);
                isStudentFound = true;
            }
        }

        if (!isStudentFound) {
            foundStudents = null;
        }

        return foundStudents;
    }

    public void studentSearchByName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Tìm kiếm hồ sơ sinh viên: ");

        ArrayList<Student> sortedStudents = sortStudentsByName();

        System.out.print("Tìm kiếm sinh viên bằng tên: ");
        String name = scan.nextLine();

        ArrayList<Student> foundStudents = searchStudentsByName(name, sortedStudents);

        int numberOfFoundStudents = foundStudents.size();
        if (numberOfFoundStudents > 0) {
            for (int i = 0; i < numberOfFoundStudents; i++) {
                Student student = foundStudents.get(i);
                System.out.println("Tìm thấy sinh viên: " + student.getName());
                student.printInfo();
                System.out.println();
            }
        } else {
            System.out.println("Không tìm thấy sinh viên: " + name);
        }
    }

    public int studentSearchByID() {
        Scanner scan = new Scanner(System.in);

        System.out.print("ID sinh viên: ");
        int id = scan.nextInt();
        scan.nextLine();

        String result = foundStudent(id);
        boolean isResultNotEmpty = !result.isEmpty();

        if (isResultNotEmpty) {
            System.out.println(result);
            return id;
        } else {
            return -1;
        }
    }

    public String foundStudent(int id) {
        boolean found = false;
        String studentName = "";

        for (int i = 0; i < student.size(); i++) {
            int currentId = student.get(i).getInt("id");

            if (currentId == id) {
                studentName = student.get(i).getName();
                found = true;
                break;
            }
        }

        if (found) {
            return "Tìm thấy sinh viên: " + studentName;
        }

        return "";
    }

    public void populate() {
        addStudent();
        addSubject();
    }

    public void addStudent() {
        student.add(new Student(12, "Slightly Wind"));
        student.add(new Student(14, "Adam Damn"));
        student.add(new Student(17, "Jane Smith"));
        student.add(new Student(13, "Erik Parker"));
        student.add(new Student(15, "Social Dude"));
        student.add(new Student(19, "Emily Davis"));
        student.add(new Student(16, "John Doe"));
        student.add(new Student(18, "Alex Johnson"));
        student.add(new Student(20, "Michael Wilson"));
    }

    public void addSubject() {
        student.get(0).addSubject(1, 2);
        student.get(0).addSubject(2, 3);

        student.get(1).addSubject(1, 3);
        student.get(1).addSubject(2, 2);

        student.get(2).addSubject(3, 1);
        student.get(2).addSubject(2, 1);

        student.get(3).addSubject(3, 3);
        student.get(3).addSubject(1, 2);

        student.get(4).addSubject(1, 4);
        student.get(4).addSubject(3, 5);

        student.get(5).addSubject(2, 6);
        student.get(5).addSubject(3, 7);

        student.get(6).addSubject(1, 8);
        student.get(6).addSubject(2, 9);

        student.get(7).addSubject(3, 4);
        student.get(7).addSubject(1, 5);

        student.get(8).addSubject(2, 6);
        student.get(8).addSubject(3, 7);
    }
}

class Student {
    protected int id;
    protected String name;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static Scanner scan = new Scanner(System.in);

    public Student(int sID, String sName) {
        id = sID;
        name = sName;
    }

    public Student(int ID) {
        id = ID;
        System.out.print("Tên sinh viên: ");
        name = scan.nextLine();
        newSubject();
        System.out.println();
    }

    public Student(Student that) {
        this.id = that.id;
        this.name = that.name;
        this.subjectList = that.subjectList;
    }

    private ArrayList<Subject> subjectList = new ArrayList<>();

    public boolean isSubjectAlreadyRegistered(int subject, int semester) {
        int subjectListSize = subjectList.size();
        Subject newSubject = new Subject(subject, semester);

        for (int i = 0; i < subjectListSize; i++) {
            Subject currentSubject = subjectList.get(i);
            boolean isSameSubject = !currentSubject.compare(newSubject);

            if (isSameSubject) {
                return true;
            }
        }

        return false;
    }

    public int  addSubject(int subject, int semester) {
        boolean isAlreadyRegistered = isSubjectAlreadyRegistered(subject, semester);

        if (isAlreadyRegistered) {
            System.out.println("Sinh viên đã có cùng môn học trong học kỳ đó!");
            return -1;
        } else {
            Subject newSubject = new Subject(subject, semester);
            subjectList.add(newSubject);
            return 1;
        }
    }

    public void newSubject() {
        printSubjectList();

        System.out.print("Môn học đăng ký: ");
        int subject = scan.nextInt();
        scan.nextLine();

        System.out.print("Học kỳ: ");
        int semester = scan.nextInt();
        scan.nextLine();

        addSubject(subject, semester);
    }

    public void printSubjectList() {
        System.out.println("Danh sách môn học: ");
        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C / C ++");
    }

    public void removeSubject() {
        System.out.print("Xóa môn học theo thứ tự: ");
        int subjectIndex = scan.nextInt();
        int adjustedIndex = subjectIndex - 1;
        subjectList.remove(adjustedIndex);
    }

    public String printSubjectName(int n) {
        switch (n) {
            case 1:
                return ("Java");
            case 2:
                return (".Net");
            case 3:
                return ("C / C ++");
        }
        return "";
    }

    public void printInfo() {
        int numberOfSubjects = 3;

        for (int subjectNumber = 1; subjectNumber <= numberOfSubjects; subjectNumber++) {
            int count = 0;
            int subjectListSize = subjectList.size();

            for (int i = 0; i < subjectListSize; i++) {
                Subject currentSubject = subjectList.get(i);
                int currentSubjectNumber = currentSubject.getInt("subject");

                boolean isSameSubject = currentSubjectNumber == subjectNumber;
                if (isSameSubject) {
                    count++;
                }
            }

            boolean isSubjectRegistered = count != 0;
            if (isSubjectRegistered) {
                String subjectName = printSubjectName(subjectNumber);
                System.out.println(id + ". " + name + " | " + subjectName + " | " + count);
            }
        }
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public int getInt(String choice) {
        switch (choice) {
            case "id":
                return id;
        }
        return 0;
    }
}

class Subject {
    private int subject, semester;

    public int getSubject() {
        return this.subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return this.semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Subject(int newSubject, int newSemester) {
        this.subject = newSubject;
        this.semester = newSemester;
    }

    public int getInt(String argument) {
        if ("subject".equals(argument)) {
            return this.subject;
        } else if ("semester".equals(argument)) {
            return this.semester;
        } else {
            return 0;
        }
    }

    public String info() {
        String subjectName = printSubjectName(this.subject);
        return subjectName + " | " + this.semester;
    }

    public String printSubjectName(int n) {
        String subjectName;
        switch (n) {
            case 1:
                subjectName = "Java";
                break;
            case 2:
                subjectName = ".Net";
                break;
            case 3:
                subjectName = "C / C ++";
                break;
            default:
                subjectName = "";
                break;
        }
        return subjectName;
    }

    public boolean compare(Subject that) {
        boolean isSameSemester = that.semester == this.semester;
        boolean isSameSubject = that.subject == this.subject;
        boolean isSame = isSameSemester && isSameSubject;
        return !isSame;
    }
}