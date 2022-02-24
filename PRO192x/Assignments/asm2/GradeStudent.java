import java.io.StreamCorruptedException;
import java.util.Scanner;

public class GradeStudent {

    public static double homework(Scanner scan) {
        System.out.println("\nHomework:"); 
        System.out.print("Number of assignments? "); int asmCount = scan.nextInt(); scan.nextLine(); //consume leftover new line
        int[][] scores = new int[asmCount + 1][2];

        for (int i = 1; i <= asmCount; i++) {
            System.out.print("Assignment " + i + " score and max: ");

            int count = 0; for (String s : scan.nextLine().split(" ")) {
                scores[i][count++] = Integer.parseInt(s);
            }
        }
        
        System.out.println();
        return 0;
    }
    

    public static double midTerm(Scanner scan, int isFinal) {
        switch(isFinal){
            case 0: System.out.println("\nMidterm:"); break;
            case 1: System.out.println("\nFinal:");   break;
        }
        
        System.out.print("Weight (0-100)? "); double weight = scan.nextInt();
        System.out.print("Score earned? ");   double score = scan.nextDouble();
        System.out.print("Were scores shifted (1=yes, 2=no)? "); 
        
        switch (scan.nextInt()) {
            case 1: System.out.print("Shift amount? "); 
                    score += scan.nextDouble();
                    if(score > 100) score = 100;
                    break;

            case 2: break;
            //noDefault
        }

        double result = Math.round((score*weight/100.0) * 10.0) / 10.0; //at least it works

        System.out.println("Total points = " + score + " / 100");
        System.out.println("Weighted score = " + result + " / " + weight);
        return result;
    }

    public static double finalTerm(Scanner scan) {
        return midTerm(scan, 1);
    }

    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }

    public static void report(double midTermScore, double finalScore, double homeworkScore){
        System.out.println(midTermScore);
        System.out.println(finalScore);
    }

    public static void main(String[] args) {
        begin();
        Scanner scan = new Scanner(System.in);
        //report(midTerm(scan,0), finalTerm(scan), homework(scan));
        System.out.println(homework(scan));
    }
}


/*
https://courses.funix.edu.vn/courses/course-v1:FUNiX+PRO192x_2.1-A_VN+2021_T3/courseware/4007356d101f474da68fa2cf9a0523c3/68bd3944dbed4b7aa35c4d2019c5ce7b/?activate_block_id=block-v1%3AFUNiX%2BPRO192x_2.1-A_VN%2B2021_T3%2Btype%40sequential%2Bblock%4068bd3944dbed4b7aa35c4d2019c5ce7b

asm2 có yêu cầu khá nhiều input của người dùng trong khoảng nhất định, nhưng không nhắc đến trường hợp người dùng nhập vào khoảng ngoài xác định/giới hạn.

Em có cần xử lý các trường hợp đó không ạ?
*/