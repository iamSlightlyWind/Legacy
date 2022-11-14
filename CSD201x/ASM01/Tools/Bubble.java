public class Bubble {

    public static double[] sort(double[] myDouble, boolean printSteps) {
        double count = myDouble.length;
        double temp;
        for (int i = 1; i < count; i++) {
            if (myDouble[i] < myDouble[i - 1]) {
                temp = myDouble[i - 1];
                myDouble[i - 1] = myDouble[i];
                myDouble[i] = temp;

                if (printSteps)
                    Utils.printStep(myDouble);
            }

            if (i == count - 1) {
                count--;
                i = 0;
            }
        }

        return myDouble;
    }
}
