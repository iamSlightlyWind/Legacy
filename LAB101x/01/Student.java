import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;

    private static Scanner scan = new Scanner(System.in);

    /////////////
    public Student(int sID, String sName) {
        id = sID;
        name = sName;
    }

    public void addSubject(int subject, int semester) {
        subjectList.add(new Subject(subject, semester));
    }
    /////////////

    public Student(int ID) {
        id = ID;
        System.out.print("Tên sinh viên: ");
        name = scan.nextLine();
        newSubject();
        System.out.println();
    }

    ///////////// Subjects control
    private ArrayList<Subject> subjectList = new ArrayList<>();

    public void newSubject() {
        System.out.println("Danh sách môn học: ");
        System.out.println("1. Java\n2. .Net\n3. C / C ++");
        System.out.print("Môn học đăng ký: ");
        int subject = scan.nextInt();

        System.out.print("Học kỳ: ");
        int semester = scan.nextInt();

        boolean check = true;

        for (int i = 0; i <= subjectList.size() - 1; i++) {
            if (!subjectList.get(i).compare(new Subject(subject, semester))) {
                System.out.println("Sinh viên đã có cùng môn học trong học kỳ đó!");
                System.out.println("Vui lòng gán môn học hoặc ngành học khác cho sinh viên: \n");
                newSubject();
                check = false;
            }
        }

        if (check) {
            subjectList.add(new Subject(subject, semester));
        }
        scan.nextLine();

    }

    public void removeSubject() {
        System.out.print("Xóa môn học theo thứ tự: ");
        subjectList.remove(scan.nextInt() - 1);
    }

    public void editSubject() {
    }

    public void printSubjects() {
        int count = 1;
        for (int i = 0; i <= subjectList.size() - 1; i++) {
            System.out.println(count++ + ". " + subjectList.get(i).info());
        }
    }

    /////////////

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

    public void printInfo() { // used for report
        for (int n = 1; n <= 3; n++) {
            int count = 0;
            for (int i = 0; i < subjectList.size(); i++) {
                if (subjectList.get(i).getInt("subject") == n) {
                    count++;
                }
            }

            if (count != 0) {
                System.out.println(getName() + " | " + printSubjectName(n) + " | " + count);
            }
        }
    }

    public Student(Student that) {
        this.id = that.getInt("id");
        this.name = that.getName();
        that.subjectList = subjectList;
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

    // private ArrayList<Integer> copySubject() {
    // ArrayList<Integer> copy = new ArrayList<Integer>(old.size());
    // subjectList.forEach(i -> {
    // copy.add(i);
    // });
    // return copy;
    // }

}