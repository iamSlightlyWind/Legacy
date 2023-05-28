import java.util.Scanner;

public class P3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Input the number of students: ");

        int n = scan.nextInt();
        String[] students = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Input the name of student " + (i + 1) + ": ");
            students[i] = scan.next();
        }

        System.out.println("The list of students: ");

        for (int i = 0; i < n; i++) {
            System.out.println(students[i].toUpperCase());
        }

        scan.close();
    }
}
