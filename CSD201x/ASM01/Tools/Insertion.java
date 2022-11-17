import java.util.Arrays;

public class Insertion {

    public static double[] sort(double[] myDouble, boolean printSteps) {

        for (int i = 1; i < myDouble.length; i++) {
            double[] sortCheck = new double[myDouble.length];
            System.arraycopy(myDouble, 0, sortCheck, 0, myDouble.length);

            double current = myDouble[i];
            int count = i - 1;

            while ((count > -1) && (myDouble[count] > current)) {
                myDouble[count + 1] = myDouble[count--];
                myDouble[count + 1] = current;
            }

            if (!Arrays.equals(myDouble, sortCheck) && printSteps) {
                Utils.printStep(myDouble);
            }

        }
        return myDouble;
    }
}