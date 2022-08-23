import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Candidate> cands = new ArrayList<>();

    public static void printMenu() {
        System.out.println("HỆ THỐNG QUẢN LÝ ỨNG VIÊN\n");
        System.out.println("1. Có kinh nghiệm");
        System.out.println("2. Fresher");
        System.out.println("3. Thực tập sinh");
        System.out.println("4. Đang tìm kiếm");
        System.out.println("5. Thoát\n");
        System.out.println(
                " (Vui lòng chọn 1 để tạo Ứng viên có kinh nghiệm, 2 để tạo ứng viên Fresher, 3 cho ứng viên thực tập sinh, 4 để tìm kiếm và 5 để thoát chương trình).");

        switch (scan.nextInt()) {
            case 1:
                createCandidateProfile(true);
                break;
            case 2:
                createCandidateProfile(false);
                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                return;
        }
    }

    public static boolean check(String attempt, String argument) {
        switch (argument) {
            case "birthYear":
                try {
                    if (attempt.length() == 4) {
                        Integer.parseInt(attempt);
                    } else
                        throw new NumberFormatException("");
                } catch (NumberFormatException ex) {
                    System.out.println("Input not recognized: birth year must be a 4 letter number");
                    return false;
                }

                return true;

            case "gradYear":
                try {
                    if (attempt.length() == 4) {
                        Integer.parseInt(attempt);
                    } else
                        throw new NumberFormatException("");
                } catch (NumberFormatException ex) {
                    System.out.println("Input not recognized: graduation year must be a 4 letter number");
                    return false;
                }

                return true;

            case "phoneNumber":
                try {
                    if (attempt.length() >= 10) {
                        Long.parseLong(attempt);
                    } else
                        throw new NumberFormatException("");
                } catch (NumberFormatException ex) {
                    System.out.println(
                            "Input not recognized: number of alphanumeric characters must be equal to or more than 10");
                    return false;
                }

                return true;

            case "emailAddress":
                int count = 0, splitLocation = 0;

                for (int i = 0; i < attempt.length(); i++) {
                    if (attempt.charAt(i) == '@') {
                        count++;
                        splitLocation = ++i;
                        if (splitLocation == 1) {
                            System.out.println("Input not recognized: username in email address cannot be empty");
                            return false;
                        }

                        String username = attempt.substring(0, splitLocation - 1);

                        if (username.charAt(username.length() - 1) == '.' || username.charAt(0) == '.') {
                            System.out.println(
                                    "Input not recognized: \".\" cannot appear at the start nor the end of the username");
                            return false;
                        }
                    }

                    if (count > 1) {
                        System.out
                                .println("Input not recognized: email address must have one occurrence of \"@\"");
                        return false;
                    }
                }

                if (count == 0) {
                    System.out.println("Input not recognized: email address must have one occurrence of \"@\"");
                    return false;
                }
                count = 0;

                String domain = attempt.substring(splitLocation, attempt.length());

                if (domain.length() < 3) {
                    System.out.println("Input not recognized: domain length must be equal to or more than 3");
                    return false;
                }

                if (domain.charAt(domain.length() - 1) == '.' || domain.charAt(0) == '.') {
                    System.out.println(
                            "Input not recognized: \".\" cannot appear at the start nor the end of the domain");
                    return false;
                }

                for (int i = 0; i < domain.length(); i++) {
                    if (domain.charAt(i) == '.') {
                        return true;
                    }
                }

                if (count == 0) {
                    System.out
                            .println("Input not recognized: domain address must have at least one occurrence of \".\"");
                    return false;
                }

                return true;

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

            case "gradRank":
                if (attempt.equals("Xuat sac") || attempt.equals("Tot") || attempt.equals("Kha")
                        || attempt.equals("Kem")) {
                    return true;
                } else {
                    System.out.println(
                            "Input not recognized: rank of graduation must either be one of the following values:");
                    System.out.println("Xuat sac");
                    System.out.println("Tot");
                    System.out.println("Kha");
                    System.out.println("Kem");
                    return false;
                }
        }

        return false;
    }

    public static void createCandidateProfile(boolean isExperienced) {
        String firstName, lastName;
        String birthYear = "", phoneNumber = "", emailAddress = "";
        boolean inputLock = false;

        String expYears = "", skill; // experienced
        String gradYear = "", gradRank = "", uni; // fresher

        System.out.print("Enter candidate's first name: ");
        firstName = scan.next();

        System.out.print("Enter candidate's last name: ");
        lastName = scan.next();

        while (!inputLock) {
            System.out.print("Enter candidate's year of birth: ");
            birthYear = scan.next();
            inputLock = check(birthYear, "birthYear");
        }
        inputLock = false;

        while (!inputLock) {
            System.out.print("Enter candidate's phone number: ");
            phoneNumber = scan.next();
            inputLock = check(phoneNumber, "phoneNumber");
        }
        inputLock = false;

        while (!inputLock) {
            System.out.print("Enter candidate's email address: ");
            emailAddress = scan.next();
            inputLock = check(emailAddress, "emailAddress");
        }
        inputLock = false;

        if (isExperienced) {
            while (!inputLock) {
                System.out.print("Enter candidate's years of experience: ");
                expYears = scan.next();
                inputLock = check(expYears, "expYears");
            }
            inputLock = false;

            System.out.print("Enter candidate's skill: ");
            skill = scan.next();

            cands.add(new Experienced(isExperienced, cands.size()));
            ((Experienced) cands.get(cands.size() - 1)).setExperienced(expYears, skill);

        }

        if (!isExperienced) {
            while (!inputLock) {
                System.out.print("Enter candidate's year of graduation: ");
                gradYear = scan.next();
                scan.nextLine();
                inputLock = check(gradYear, "gradYear");
            }
            inputLock = false;

            while (!inputLock) {
                System.out.print("Enter candidate's rank of graduation: ");
                gradRank = scan.nextLine();
                inputLock = check(gradRank, "gradRank");
            }
            inputLock = false;

            System.out.print("Enter candidate's graduated university: ");
            uni = scan.next();

            cands.add(new Fresher(isExperienced, cands.size()));
            ((Fresher) cands.get(cands.size() - 1)).setFresher(gradYear, gradRank, uni);
        }

        cands.get(cands.size() - 1).setName(firstName, lastName);
        cands.get(cands.size() - 1).setInfo(birthYear, phoneNumber, emailAddress);

    }

    public static void main(String[] args) {
        printMenu();
        printMenu();
        printMenu();
        printMenu();
    }
}