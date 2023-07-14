package DTO;

import java.util.Scanner;

public class Verify {
    private static Scanner scan = new Scanner(System.in);

    public static String getString(String type) {
        String value;

        do {
            System.out.print("Enter " + type + ": ");
            value = scan.nextLine();
            if (value.replace(" ", "").equals("")) {
                System.out.println("Value of " + type + " can't be empty!");
            }
        } while (value.replace(" ", "").equals(""));

        return value;
    }

    public static int getInt(String type) {
        int value;

        do {
            System.out.print("Enter " + type + ": ");
            value = Integer.parseInt(scan.nextLine());
            if (value <= 0) {
                System.out.println("Value of " + type + " must be > 0!");
            }
        } while (value <= 0);

        return value;
    }

    public static boolean getBoolean(String type){
        String value;

        do {
            System.out.print("Enter " + type + ": ");
            value = scan.nextLine();
            if (!value.equals("true") && !value.equals("false")) {
                System.out.println("Value of " + type + " must be true or false!");
            }
        } while (!value.equals("true") && !value.equals("false"));

        return Boolean.parseBoolean(value);
    }

}