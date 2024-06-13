package student;

import java.util.ArrayList;
import java.util.Scanner;

import subject.Subject;

public class Student {
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

    public int addSubject(int subject, int semester) {
        boolean isAlreadyRegistered = isSubjectAlreadyRegistered(subject, semester);
        if (subject > 3)
            return -1;

        if (semester < 1)
            return -1;

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

    public void printCurrentSubjects() {
        int count = 1;
        for (int i = 0; i <= subjectList.size() - 1; i++) {
            System.out.println(count++ + ". " + subjectList.get(i).info());
        }
    }

    public void printSubjectList() {
        System.out.println("Danh sách môn học: ");
        System.out.println("1. Java");
        System.out.println("2. .Net");
        System.out.println("3. C / C ++");
    }

    public int removeSubject(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        subjectList.remove(index);
        return 1;
    }

    public String subjectList(int n) {
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

    public boolean isLearningSubject(int subjectId) {
        for (Subject subject : subjectList) {
            if (subject.getInt("subject") == subjectId) {
                return true;
            }
        }
        return false;
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
                String subjectName = subjectList(subjectNumber);
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