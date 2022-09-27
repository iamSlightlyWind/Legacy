import java.util.concurrent.TimeUnit;

public class Main {

    static int arraySize = Utilities.getArraySize();
    static int[] myInt = Utilities.getPreUnsortedInts();
    // static int[] myInt = Utilities.getPreSortedInts();

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        // System.out.println(Euclid.calc(88, 64));

        // Bubble.sort();
        // Selection.sort();
        // Insertion.sort();

        long endTime = System.nanoTime();

        Utilities.checkSorted(myInt);

        System.out.println("\nrun time: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime) + "ms");
    }
}
