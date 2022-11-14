public class Selection {

    public static double[] sort(double[] myDouble, boolean printSteps) {
        for (int i = 0; i < myDouble.length; i++) {
            double temp;
            for (int x = i + 1; x < myDouble.length; x++) {
                if (myDouble[x] < myDouble[i]) {
                    temp = myDouble[i];
                    myDouble[i] = myDouble[x];
                    myDouble[x] = temp;

                    if (printSteps)
                        Utils.printStep(myDouble);
                }
            }
        }

        return myDouble;
    }
}
