import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void printMenu() throws IOException {
        System.out.println("\n\n");
        System.out.println("+-------------------Menu------------------+");
        System.out.println("|      1. Input                           |");
        System.out.println("|      2. Output                          |");
        System.out.println("|      3. Bubble sort                     |");
        System.out.println("|      4. Selection sort                  |");
        System.out.println("|      5. Insertion sort                  |");
        System.out.println("|      6. Search > value                  |");
        System.out.println("|      7. Search = value                  |");
        System.out.println("|      0. Exit                            |");
        System.out.println("+-----------------------------------------+");
        System.out.print("Choice: ");
        int choice = scan.nextInt();
        System.out.println("\n");

        switch (choice) {
            case 0:
                System.exit(0);
                break;
            case 1:
                getInput();
                break;
            case 2:
                readFromFile();
                break;
            case 3:
                System.out.println("Bubble sorting: ");
                writeToFile(Bubble.sort(readFromFile(), true), "OUTPUT1.TXT");
                break;
            case 4:
                System.out.println("Selection sorting: ");
                writeToFile(Selection.sort(readFromFile(), true), "OUTPUT2.TXT");
                break;
            case 5:
                System.out.println("Insertion sorting: ");
                writeToFile(Insertion.sort(readFromFile(), true), "OUTPUT3.TXT");
                break;
            case 6:
                System.out.print("Enter value for linear search: ");
                writeToFile(Utils.linearSearch(scan.nextDouble(), readFromFile()), "OUTPUT4.TXT");
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    public static void writeToFile(double[] myDouble, String dest) throws IOException {
        FileWriter writer = new FileWriter(dest);
        writer.write(myDouble.length + "\n");

        for (double i : myDouble) {
            writer.write(i + "\n");
        }

        writer.close();
    }

    public static double[] readFromFile() throws IOException {
        System.out.print("Current array: ");
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        double[] myDouble = new double[Integer.parseInt(reader.readLine())];

        for (int i = 0; i < myDouble.length; i++) {
            myDouble[i] = Double.parseDouble(reader.readLine());
            System.out.print(myDouble[i] + " ");
        }

        reader.close();
        System.out.println();
        return myDouble;
    }

    public static void getInput() throws IOException {
        System.out.print("Amount of input(s): ");
        int count = scan.nextInt();
        double[] input = new double[count];

        for (int i = 0; i < input.length; i++) {
            System.out.print("#" + (i + 1) + ": ");
            input[i] = scan.nextDouble();
        }

        writeToFile(input, "INPUT.TXT");

    }

    public static void main(String[] args) throws IOException {
        while (true) {
            printMenu();
        }
    }
}