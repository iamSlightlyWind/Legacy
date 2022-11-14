public class Insertion {

    public static double[] push(double[] myDouble, int index, int location) {
        for (double temp; index > location; index--) {
            temp = myDouble[index];
            myDouble[index] = myDouble[index - 1];
            myDouble[index - 1] = temp;
        }

        return myDouble;
    }

    public static double[] sort(double[] myDouble, boolean printSteps) {

        if (myDouble[1] < myDouble[0]) {
            myDouble = push(myDouble, 1, 0);
        }

        for (int i = 2; i < myDouble.length; i++) {
            for (int count = i - 1, x = i; count > 0; count--) {
                if (myDouble[x] > myDouble[count]) {
                    break;
                } else if (myDouble[x] < myDouble[count] && myDouble[x] > myDouble[count - 1]) {
                    myDouble = push(myDouble, x, count);

                    if (printSteps)
                        Utils.printStep(myDouble);
                } else if (myDouble[x] < myDouble[0]) {
                    myDouble = push(myDouble, x, 0);

                    if (printSteps)
                        Utils.printStep(myDouble);
                }
            }
        }

        return myDouble;
    }
}