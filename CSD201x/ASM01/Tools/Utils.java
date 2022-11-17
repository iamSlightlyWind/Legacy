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
        for (double i : finals) {
            System.out.print((int) i + " ");
        }
        return finals;
    }
}
