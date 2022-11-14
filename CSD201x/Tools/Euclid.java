public class Euclid {

    public static int calc(int a, int b) {
        int result = -1;

        while (result != 0) {
            result = a % b;
            a = b;
            b = result;
        }

        return a;
    }
}