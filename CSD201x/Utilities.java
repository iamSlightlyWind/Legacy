import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utilities {

    public static void push(int index, int location) {
        for (int temp; index > location; index--) {
            temp = Main.myInt[index];
            Main.myInt[index] = Main.myInt[index - 1];
            Main.myInt[index - 1] = temp;
        }
    }

    public static int getArraySize() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("ArraySize"));
            return (scanner.nextInt());
        } catch (FileNotFoundException e) {
        }
        return 0;
    }

    public static void checkSorted() {
        int count = 0;
        for (int i = 0; i < Main.myInt.length - 1; i++) {
            if (Main.myInt[i] < Main.myInt[i + 1]) {
                count++;
            } else
                break;
        }

        count++;

        System.out.println("Result: " + count + "/" + Main.myInt.length);

    }

    public static int[] getPreSortedInts() {
        int[] myInt = new int[Main.arraySize];
        try (Scanner scanner = new Scanner(new File("PreSortedInts"))) {
            int i = 0;
            while (scanner.hasNextInt()) {
                myInt[i++] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
        }
        return myInt;
    }

    public static int[] getPreUnsortedInts() {
        int[] myInt = new int[Main.arraySize];
        try (Scanner scanner = new Scanner(new File("PreUnsortedInts"))) {
            int i = 0;
            while (scanner.hasNextInt()) {
                myInt[i++] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
        }
        return myInt;
    }
}
