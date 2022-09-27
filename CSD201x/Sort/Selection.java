public class Selection {

    public static void sort() {
        for (int i = 0; i < Main.myInt.length; i++) {
            for (int x = i + 1, temp; x < Main.myInt.length; x++) {
                if (Main.myInt[x] < Main.myInt[i]) {
                    temp = Main.myInt[i];
                    Main.myInt[i] = Main.myInt[x];
                    Main.myInt[x] = temp;
                }
            }
        }
    }
}
