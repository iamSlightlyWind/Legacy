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
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    public static void writeToFile(int[] myInt, String dest) throws IOException {
        FileWriter writer = new FileWriter(dest);
        writer.write(myInt.length + "\n");

        for (int i : myInt) {
            writer.write(i + "\n");
        }

        writer.close();
    }

    public static int[] readFromFile() throws IOException {
        System.out.print("Current: ");
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        int[] myInt = new int[Integer.parseInt(reader.readLine())];

        for (int i = 0; i < myInt.length; i++) {
            myInt[i] = Integer.parseInt(reader.readLine());
            System.out.print(myInt[i] + " ");
        }

        reader.close();
        System.out.println();
        return myInt;
    }

    public static void getInput() throws IOException {
        System.out.print("Amount of input(s): ");
        int count = scan.nextInt();
        int[] input = new int[count];

        for (int i = 0; i < input.length; i++) {
            System.out.print("#" + (i + 1) + ": ");
            input[i] = scan.nextInt();
        }

        writeToFile(input, "INPUT.TXT");

    }

    public static void main(String[] args) throws IOException {
        while (true) {
            printMenu();
        }
    }
}