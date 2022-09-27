public class Insertion {

    public static void sort() {

        if (Main.myInt[1] < Main.myInt[0]) {
            Main.myInt = Utilities.push(Main.myInt, 1, 0);
        }

        for (int i = 2; i < Main.myInt.length; i++) {
            for (int count = i - 1, x = i; count > 0; count--) {
                if (Main.myInt[x] > Main.myInt[count]) {
                    break;
                } else if (Main.myInt[x] < Main.myInt[count] && Main.myInt[x] > Main.myInt[count - 1]) {
                    Main.myInt = Utilities.push(Main.myInt, x, count);
                } else if (Main.myInt[x] < Main.myInt[0]) {
                    Main.myInt = Utilities.push(Main.myInt, x, 0);
                }
            }
        }
    }
}