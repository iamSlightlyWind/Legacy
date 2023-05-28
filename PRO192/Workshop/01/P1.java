import java.util.Scanner;

public class P1 {
    public static void main(String[] args) {
        int rows, columns, sum = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        columns = scanner.nextInt();

        int[][] matrix = new int[rows][columns];

        System.out.println("Enter the matrix");
        scanner.nextLine();

        for (boolean input = true;;) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (input) {
                        System.out.print("matrix[" + i + "][" + j + "]=");
                        matrix[i][j] = scanner.nextInt();
                        sum += matrix[i][j];
                    } else {
                        System.out.print(matrix[i][j] + " ");
                    }
                }
            }
            if (!input) {
                System.out.println("\nSum: " + sum);
                System.out.println("Average: " + ((sum / (float) (rows * columns))));
                break;
            } else {
                System.out.println("Matrix inputted:");
                input = false;
            }
        }
    }
}
