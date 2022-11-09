import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utilities {

    public static int[] push(int[] myInt, int index, int location) {
        for (int temp; index > location; index--) {
            temp = myInt[index];
            myInt[index] = myInt[index - 1];
            myInt[index - 1] = temp;
        }

        return myInt;
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

    public static void checkSorted(int[] myInt) {
        int count = 0;
        for (int i = 0; i < myInt.length - 1; i++) {
            if (myInt[i] < myInt[i + 1]) {
                count++;
            } else
                break;
        }

        count++;

        System.out.println("Result: " + count + "/" + myInt.length);

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

    public static void printStep(int[] myInt) {
        for (int i : myInt) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
