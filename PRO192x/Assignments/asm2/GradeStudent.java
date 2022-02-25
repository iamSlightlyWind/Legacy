import java.util.Scanner;

public class GradeStudent {

    public static double inRange(double control, double starting, double ending, Scanner scan) {//failsafe... implementation?
        while (control < starting) {
            if (ending > starting || control > ending) {
                System.out.println("Value must be larger than " + (int) starting + " and smaller than " + (int) ending + ".");
            }else {
                System.out.println("Value must be larger than " + (int) starting);
                ending = Integer.MAX_VALUE;
            }
            System.out.print("Re-enter value: "); control = scan.nextDouble();
        }
        return control;
    }

    public static double homework(Scanner scan) {
        System.out.println("\nHomework:");
        System.out.print("Weight (0-100)? "); double weight = inRange(scan.nextInt(), 0, 100, scan);
        System.out.print("Number of assignments? "); double asmCount = inRange(scan.nextInt(), 0, -1, scan);
        scan.nextLine(); //consume leftover new line

        
        double[] scores = new double[2];//scores[0] = student score; scores[1] = max score
        scores[1] = 30;//max score without hw = max attendance score

        for (int i = 1; i <= asmCount; i++) {
            boolean manualFailSafe = false;
            while (manualFailSafe == false) {
                System.out.print("Assignment " + i + " score and max: ");

                int count = 0; double[] temp = new double[2];
                for (String s : scan.nextLine().split(" ")) {
                    temp[count++] += Integer.parseInt(s);
                }

                if (temp[0] < 0 || temp[0] > temp[1]) {
                    System.out.println("Assigment score must be higher than 0 and lower than max assignment score");
                    manualFailSafe = false;
                    continue;
                } else {
                    scores[0] += temp[0];
                    scores[1] += temp[1];
                    manualFailSafe = true;
                }
            }
        }

        System.out.print("How many sections did you attend? "); double attended = inRange(scan.nextInt(), 0, 5, scan);
        System.out.println("Section points = " + (int) attended*5 + " / 30"); scores[0] += attended * 5;
        System.out.println("Total points = " + (int) scores[0] + " / " + (int) scores[1]);
        
        double result = Math.round((scores[0] * weight / scores[1]) * 10.0) / 10.0; //at least it works

        System.out.println("Weighted score = " + result + " / " + weight);
        
        return result;
    }
    
    public static double midTerm(Scanner scan, int isFinal) {
        switch(isFinal){
            case 0: System.out.println("\nMidterm:"); break;
            case 1: System.out.println("\nFinal:");   break;
        }
        
        System.out.print("Weight (0-100)? "); double weight = inRange(scan.nextInt(), 0, 100, scan);
        System.out.print("Score earned? ");   double score = inRange(scan.nextDouble(), 0, 100, scan);
        System.out.print("Were scores shifted (1=yes, 2=no)? "); 
        
        switch (scan.nextInt()) {
            case 1: System.out.print("Shift amount? "); 
                    score += inRange(scan.nextDouble(), 0, -1, scan);
                    if(score > 100) score = 100;
                    break;

            case 2: break;
            //noDefault
        }

        double result = Math.round((score * weight / 100.0) * 10.0) / 10.0;

        System.out.println("Total points = " + (int) score + " / 100");
        System.out.println("Weighted score = " + result + " / " + (int) weight);
        return result;
    }

    public static double finalTerm(Scanner scan) {return midTerm(scan, 1);}

    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }

    public static void report(double midTermScore, double finalScore, double homeworkScore){
        double score = midTermScore + finalScore + homeworkScore;
        System.out.println("\nOverall percentage = " + score);
        System.out.print("Your grade will be at least: ");

        if (score >= 85) {
            System.out.println("3.0");
        } else if (score >= 75) {
            System.out.println("2.0");
        } else if (score >= 60) {
            System.out.println("1.0");
        } else
            System.out.println("0.0");

        System.out.println("\033[3m<<your custom grade message here>>\033[3m");//italic, most shell should be able to handle this
    }

    public static void main(String[] args) {
        begin();
        Scanner scan = new Scanner(System.in);
        //System.out.println(homework(scan));
        report(midTerm(scan, 0), finalTerm(scan), homework(scan));
    }
}