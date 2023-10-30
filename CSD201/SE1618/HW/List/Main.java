public class Main {
    public static void main(String[] args) {
        ArrayManagementTest();
    }

    public static void ArrayManagementTest() {
        int[] arr = { 1, 2, 3, 4, 5 };

        System.out.println(ArrayManagement.sum(arr, 0));
        System.out.println(ArrayManagement.min(arr, 0));
        System.out.println(ArrayManagement.max(arr, 0));
        System.out.println(ArrayManagement.sorted(arr, 0));

        System.out.println("\n\n\n");

        int[] arr2 = { 1, 2, 3, 4, 5, 9, 7, 8, 0 };

        System.out.println(ArrayManagement.sum(arr2, 0));
        System.out.println(ArrayManagement.min(arr2, 0));
        System.out.println(ArrayManagement.max(arr2, 0));
        System.out.println(ArrayManagement.sorted(arr2, 0));
    }
}