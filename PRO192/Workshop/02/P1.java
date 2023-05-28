import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            try {
                System.out.print("Enter the number: ");
                n = sc.nextInt();
                if (n < 1) {
                    System.out.println("The number is invalid");
                } else {
                    System.out.println("The number is " + n);
                }
            } catch (Exception e) {
                System.out.println("The number is invalid");
                sc.nextLine();
            }
        } while (true);
    }
}