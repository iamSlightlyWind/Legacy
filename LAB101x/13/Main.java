public class Main {
    static int count = 1;

    static void printFib(int num1, int num2) {
        System.out.print(num1 + " ");

        int next = num1 + num2;
        num1 = num2;
        num2 = next;

        if(count == 45) System.exit(0);
        count++;
        printFib(num1, num2);
    }

    public static void main(String[] args) {

        int num1 = 0, num2 = 1;
        System.out.println("The 45 sequence fibonacci: ");

        printFib(num1, num2);
    }
}