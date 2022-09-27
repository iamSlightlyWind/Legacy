public class Bubble {

    public static void sort() {
        int count = Main.myInt.length;
        for (int i = 1, temp; i < count; i++) {
            if (Main.myInt[i] < Main.myInt[i - 1]) {
                temp = Main.myInt[i - 1];
                Main.myInt[i - 1] = Main.myInt[i];
                Main.myInt[i] = temp;
            }

            if (i == count - 1) {
                count--;
                i = 0;
            }
        }
    }
}
