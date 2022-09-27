public class Insertion {

    public static void sort() {

        if (Main.myInt[1] < Main.myInt[0]) {
            Utilities.push(1, 0);
        }

        for (int i = 2; i < Main.myInt.length; i++) {
            for (int count = i - 1, x = i; count > 0; count--) {
                if (Main.myInt[x] > Main.myInt[count]) {
                    break;
                } else if (Main.myInt[x] < Main.myInt[count] && Main.myInt[x] > Main.myInt[count - 1]) {
                    Utilities.push(x, count);
                } else if (Main.myInt[x] < Main.myInt[0]) {
                    Utilities.push(x, 0);
                }
            }
        }
    }
}