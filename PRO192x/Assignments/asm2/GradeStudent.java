import java.util.Scanner;

public class GradeStudent {

    public static double midTerm(Scanner scan) {
        System.out.print("Midterm:");
        System.out.print("Weight (0-100)? "); int weight = scan.nextInt();
        System.out.print("Score earned? ");   int score = scan.nextInt();
        System.out.print("Were scores shifted (1=yes,2=no)? "); 
        
        switch (scan.nextInt()) {
            case 1: System.out.print("Shift amount? "); 
                    score += scan.nextInt();
                    if(score > 100) score = 100;
                    break;

            case 2: break;
            //noDefault
        }

        System.out.println("Total points = " + score + " / 100");
        System.out.println("Weighted score = " + score/100*weight + " / " + weight);
        return score/100*weight;
    }

    public static double finalTerm(Scanner scan) {
        return 0;
    }

    public static double homework(Scanner scan) {
        return 0;
    }

    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }

    public static void report(double midTermScore, double finalScore, double homeworkScore){

    }

    public static void main(String[] args) {
        begin();
        Scanner scan = new Scanner(System.in);
        report(midTerm(scan), finalTerm(scan), homework(scan));
    }
}


/*
https://courses.funix.edu.vn/courses/course-v1:FUNiX+PRO192x_2.1-A_VN+2021_T3/courseware/4007356d101f474da68fa2cf9a0523c3/68bd3944dbed4b7aa35c4d2019c5ce7b/?activate_block_id=block-v1%3AFUNiX%2BPRO192x_2.1-A_VN%2B2021_T3%2Btype%40sequential%2Bblock%4068bd3944dbed4b7aa35c4d2019c5ce7b

asm2 có yêu cầu khá nhiều input của người dùng trong khoảng nhất định, nhưng không nhắc đến trường hợp người dùng nhập vào khoảng ngoài xác định/giới hạn. Em có cần xử lý các trường hợp đó không ạ?
*/