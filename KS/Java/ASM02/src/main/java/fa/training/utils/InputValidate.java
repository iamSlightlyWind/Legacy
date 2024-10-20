package fa.training.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidate {
    public static double inputDouble(String message, String errorMessage, String boundaryErrorMessage, Double minBoundary, Double maxBoundary) {
        if (minBoundary == null) {
            minBoundary = Double.MIN_VALUE;
        }
        if (maxBoundary == null) {
            maxBoundary = Double.MAX_VALUE;
        }
        Scanner scanner = new Scanner(System.in);
        inputLoop: while (true) {
            try {
                System.out.println(message);
                double number = Double.parseDouble(scanner.nextLine());
                if (number < minBoundary || number > maxBoundary) {
                    System.out.println(boundaryErrorMessage);
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static Integer inputInteger(String message, String errorMessage, String boundaryErrorMessage, Integer minBoundary, Integer maxBoundary) {
        if (minBoundary == null) {
            minBoundary = Integer.MIN_VALUE;
        }
        if (maxBoundary == null) {
            maxBoundary = Integer.MAX_VALUE;
        }
        Scanner scanner = new Scanner(System.in);
        inputLoop: while (true) {
            try {
                System.out.println(message);
                int number = Integer.parseInt(scanner.nextLine());
                if (number < minBoundary || number > maxBoundary) {
                    System.out.println(boundaryErrorMessage);
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static Long inputLong(String message, String errorMessage, String boundaryErrorMessage, Long minBoundary, Long maxBoundary) {
        if (minBoundary == null) {
            minBoundary = Long.MIN_VALUE;
        }
        if (maxBoundary == null) {
            maxBoundary = Long.MAX_VALUE;
        }
        Scanner scanner = new Scanner(System.in);
        inputLoop: while (true) {
            try {
                System.out.println(message);
                long number = Long.parseLong(scanner.nextLine());
                if (number < minBoundary || number > maxBoundary) {
                    System.out.println(boundaryErrorMessage);
                }
                return number;
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static LocalDate inputLocalDate(String message, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        inputLoop: while (true) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.println(message);
                String date = scanner.nextLine();
                return LocalDate.parse(date, dtf);
            } catch (DateTimeParseException e) {
                System.err.println(errorMessage);
            }
        }
    }

    public static String inputRegex(String message, String errorMessage, String regex) {
        Scanner scanner = new Scanner(System.in);
        inputLoop: while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if (input.matches(regex)) {
                return input;
            } else {
                System.err.println(errorMessage);
            }
        }
    }

    public static String inputString(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}
