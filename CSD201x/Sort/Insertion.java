public class Insertion {

    public static int[] sort(int[] myInt, boolean printSteps) {

        if (myInt[1] < myInt[0]) {
            myInt = Utilities.push(myInt, 1, 0);
        }

        for (int i = 2; i < myInt.length; i++) {
            for (int count = i - 1, x = i; count > 0; count--) {
                if (myInt[x] > myInt[count]) {
                    break;
                } else if (myInt[x] < myInt[count] && myInt[x] > myInt[count - 1]) {
                    myInt = Utilities.push(myInt, x, count);

                    if (printSteps)
                        Utilities.printStep(myInt);
                } else if (myInt[x] < myInt[0]) {
                    myInt = Utilities.push(myInt, x, 0);

                    if (printSteps)
                        Utilities.printStep(myInt);
                }
            }
        }

        return myInt;
    }
}