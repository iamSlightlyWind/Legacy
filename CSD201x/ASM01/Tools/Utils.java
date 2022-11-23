public class Utils {
    public static void printStep(double[] myDouble) {
        for (double i : myDouble) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static double[] linearSearch(double value, double[] myDouble) {
        double[] results = new double[myDouble.length];
        int count = 0;

        for (int i = 0; i < myDouble.length; i++) {
            if (myDouble[i] > value) {
                results[count++] = i;
            }
        }
        double[] finals = new double[count];
        System.arraycopy(results, 0, finals, 0, count);

        System.out.print("Items bigger than value located: ");
        if (finals.length == 0)
            System.out.print("none!");

        for (double i : finals) {
            System.out.print("\n[" + (int) i + "]: " + myDouble[(int) i]);
        }

        return finals;
    }

    public static int binarySearch(double value, double[] myDouble) {
        Insertion.sort(myDouble, false);

        int low = 0;
        int high = myDouble.length - 1;
        int mid = (high + low) / 2;

        for (;;) {
            if (value < myDouble[mid]) {
                high = mid;
            } else if (value > myDouble[mid]) {
                low = mid;
            } else if (value == myDouble[mid]) {
                break;
            }
            mid = (high + low) / 2;

            if (high - low == 2)
                break;
        }

        if (value == myDouble[mid]) {
            System.out.println("[" + mid + "]: " + myDouble[mid]);
            return mid;
        }

        return -1;

    }
}
