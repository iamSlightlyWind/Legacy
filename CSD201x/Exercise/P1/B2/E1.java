import java.util.Scanner;

public class E1{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int[] a = new int[n];

        for(int i = 0; i < n; i++){
            a[i] = scan.nextInt();
        }

        a = Selection.sort(a, true);
        System.out.println();
    }
}