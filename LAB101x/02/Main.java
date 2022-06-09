import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

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