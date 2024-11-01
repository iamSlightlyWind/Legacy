package fa.training.problem02.utils;

import java.sql.Date;
import java.util.Scanner;

public class Validator {
    public static String personName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter person name: ");
        return scanner.nextLine();
    }

    public static Date getDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        return Date.valueOf(scanner.nextLine());
    }

    public static int getID(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter person ID: ");
        return scanner.nextInt();
    }

    public static String getTitle(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter video title: ");
        return scanner.nextLine();
    }

    public static int getDuration(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter video duration: ");
        return scanner.nextInt();
    }

    public static String getURL(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter video URL: ");
        return scanner.nextLine();
    }

    public static Date getUploadDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter upload date (yyyy-mm-dd): ");
        return Date.valueOf(scanner.nextLine());
    }

    public static boolean isPublished(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Is published? (true/false): ");
        return scanner.nextBoolean();
    }

    public static int getLimit(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter limit: ");
        return scanner.nextInt();
    }
}
