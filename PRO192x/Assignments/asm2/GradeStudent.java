import java.util.Scanner;

public class GradeStudent {

    public static double homework(Scanner scan) {
        System.out.println("\nHomework:");
        System.out.print("Weight (0-100)? "); int weight = scan.nextInt();
        System.out.print("Number of assignments? "); int asmCount = scan.nextInt(); scan.nextLine(); //consume leftover new line
        double[] scores = new double[2];//scores[0] = student score; scores[1] = max score
        scores[1] = 30;//max score without hw = max attendance score

        for (int i = 1; i <= asmCount; i++) {
            System.out.print("Assignment " + i + " score and max: ");

            int count = 0; for (String s : scan.nextLine().split(" ")) {
                scores[count++] += Integer.parseInt(s);
            }
        }

        System.out.print("How many sections did you attend? "); int attended = scan.nextInt();
        System.out.println("Section points = " + attended*5 + " / 30"); scores[0] += attended*5;
        System.out.println("Total points = " + (int) scores[0] + " / " + (int) scores[1]);
        
        double result = Math.round((scores[0]*weight/scores[1]) * 10.0) / 10.0; //at least it works

        System.out.println("Weighted score = " + result + " / " + weight);
        
        return result;
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

        System.out.println("Total points = " + (int) score + " / 100");
        System.out.println("Weighted score = " + result + " / " + (int) weight);
        return result;
    }

    public static double finalTerm(Scanner scan) {
        return midTerm(scan, 1);
    }

    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }

    public static void report(double midTermScore, double finalScore, double homeworkScore){
        double score = midTermScore + finalScore + homeworkScore;
        System.out.println("\nOverall percentage = " + score);
        System.out.print("Your grade will be at least: ");
        if(score >= 85){
            System.out.println("3.0");
        }else if(score >= 75){
            System.out.println("2.0");
        }else if(score >= 60){
            System.out.println("1.0");
        }else System.out.println("0.0");

    }

    public static void main(String[] args) {
        begin();
        Scanner scan = new Scanner(System.in);
        report(midTerm(scan,0), finalTerm(scan), homework(scan));
    }
}