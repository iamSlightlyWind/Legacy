public class Main {
    public static void main(String[] args) {

        int num1 = 0, num2 = 1;
        System.out.println("The 45 sequence fibonacci: ");

        for (int i = 0; i < 45; i++) {
            System.out.print(num1 + " ");

            int next = num1 + num2;
            num1 = num2;
            num2 = next;
        }
    }
}