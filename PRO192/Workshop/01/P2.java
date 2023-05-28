import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        float a, b, result = 0;
        Scanner scan = new Scanner(System.in);

        System.out.print("Input the number 1: ");
        a = scan.nextFloat();
        System.out.print("Input the number 2: ");
        b = scan.nextFloat();

        System.out.print("Input the operator: ");
        char c = scan.next().charAt(0);

        switch (c) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '/':
                result = a / b;
                break;
            case '*':
                result = a * b;
                break;
            default:
                break;
        }
        System.out.println("the result of " + a + c + b + "=" + result);
    }
}
