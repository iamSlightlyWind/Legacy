package fa.training.problem01.utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validator {
    private static int contentMin = 50000;
    private static int contentMax = 1000000;

    public static boolean contentLengthCheck(String content) {
        if (content.length() >= contentMin && content.length() <= contentMax) {
            return true;
        }

        return false;
    }

    public static int authorID() {
        System.out.print("Enter Author ID: ");
        int author = -1;
        Scanner scanner = new Scanner(System.in);

        try {
            author = Integer.parseInt(scanner.nextLine());

            if (author < 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number larger than 0.");
            author = authorID();
        }

        return author;
    }

    public static String getContent() {
        System.out.print("Enter Content: ");
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();

        if (contentLengthCheck(content)) {
            return content;
        } else {
            System.out.println("Content length must be between 50,000 and 1,000,000 characters.");
            return getContent();
        }
    }

    public static String getTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Title: ");
        return scanner.nextLine();
    }

    public static boolean checkLeapYear(String year) {
        int yearInt = Integer.parseInt(year);
        if (yearInt % 4 == 0) {
            if (yearInt % 100 == 0) {
                if (yearInt % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static Date getDate() {
        System.out.print("Enter Date (yyyy-MM-dd): ");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();

        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date result = null;
        try {
            result = dateFormat.parse(date);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
            result = getDate();
        }
        return result;
    }
}
