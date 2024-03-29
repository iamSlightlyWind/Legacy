public class Bubble {

    public static int[] sort(int[] myInt, boolean printSteps) {
        int count = myInt.length;
        for (int i = 1, temp; i < count; i++) {
            if (myInt[i] < myInt[i - 1]) {
                temp = myInt[i - 1];
                myInt[i - 1] = myInt[i];
                myInt[i] = temp;

                if (printSteps)
                    Utils.printStep(myInt);
            }

            if (i == count - 1) {
                count--;
                i = 0;
            }
        }

        return myInt;
    }
}
