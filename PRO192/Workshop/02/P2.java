import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            try {
                System.out.print("Enter the string: ");
                s = sc.nextLine();
                if (s.matches("SE\\d{3}")) {
                    System.out.println("The string is " + s);
                } else {
                    System.out.println("The string is invalid");
                }
            } catch (Exception e) {
                System.out.println("The string is invalid");
                sc.nextLine();
            }
        } while (true);
    }
}