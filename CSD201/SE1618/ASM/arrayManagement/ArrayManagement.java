public class ArrayManagement {
    public static int sum(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        return arr[index] + sum(arr, index + 1);
    }

    public static int min(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        int min = min(arr, index + 1);
        if (arr[index] < min) {
            return arr[index];
        }
        return min;
    }

    public static int max(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        int max = max(arr, index + 1);
        if (arr[index] > max) {
            return arr[index];
        }
        return max;
    }

    public static boolean sorted(int[] arr, int index) {
        if (index == arr.length - 1) {
            return true;
        }
        if (arr[index] > arr[index + 1]) {
            return false;
        }
        return sorted(arr, index + 1);
    }
}