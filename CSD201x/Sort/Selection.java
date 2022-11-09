public class Selection {

    public static int[] sort(int[] myInt, boolean printStep) {
        for (int i = 0; i < myInt.length; i++) {
            for (int x = i + 1, temp; x < myInt.length; x++) {
                if (myInt[x] < myInt[i]) {
                    temp = myInt[i];
                    myInt[i] = myInt[x];
                    myInt[x] = temp;
                    
                    if (printStep)
                        Utilities.printStep(myInt);
                }
            }
        }

        return myInt;
    }
}
