import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Candidate> cands = new ArrayList<>();

    public static void main(String[] args) {
        String attempt = "5t";

    }

    public static boolean check(String attempt, String argument) {
        switch (argument) {
            case "birthDate":
                try {
                    if (attempt.length() == 4) {
                        Integer.parseInt(attempt);
                    } else
                        throw new NumberFormatException("");
                } catch (NumberFormatException ex) {
                    System.out.println("Input not recognized: birth date must be a 4 letter number");
                    return false;
                }

                return true;

            case "phoneNumber":
                try {
                    if (attempt.length() == 10) {
                        Integer.parseInt(attempt);
                    } else
                        throw new NumberFormatException("");
                } catch (NumberFormatException ex) {
                    System.out.println("Input not recognized: phone number must be a 10 letter number");
                    return false;
                }

                return true;

            case "emailAddress":
                break;

            case "expYears":
                try {
                    if (Integer.parseInt(attempt) >= 0 && Integer.parseInt(attempt) <= 100) {
                        return true;
                    } else
                        throw new NumberFormatException("");
                } catch (NumberFormatException ex) {
                    System.out.println("Input not recognized: phone number must be a 10 letter number");
                    return false;
                }

            case "ranking":
                break;
        }

        return false;
    }

    public static void createCandidateProfile() {
        System.out.println("Creating a candidate profile:");
        System.out.println("");
    }

    public void printMenu() {
        System.out.println("HỆ THỐNG QUẢN LÝ ỨNG VIÊN\n");
        System.out.println("1 .Có kinh nghiệm");
        System.out.println("2 .Fresher");
        System.out.println("3 .Thực tập sinh");
        System.out.println("4 .Đang tìm kiếm");
        System.out.println("5 .Thoát\n");
        System.out.println(
                " (Vui lòng chọn 1 để tạo Ứng viên có kinh nghiệm, 2 để tạo ứng viên Fresher, 3 cho ứng viên thực tập sinh, 4 để tìm kiếm và 5 để thoát chương trình).");

        switch (scan.nextInt()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                return;
        }
    }
}