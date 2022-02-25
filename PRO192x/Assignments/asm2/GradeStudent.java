import java.util.Locale;    //double use dot instead of comma
import java.util.Scanner;

public class GradeStudent {

    public static double inRange(double control, double starting, double ending, Scanner scan) {    //making sure that variables stay within limit
        for(;;){
            if (starting > ending) {                                                                //variable can be as big as possible
                if (control > starting) break;
                System.out.println("Value must be equals to or larger than " + (int) starting);
                ending = Integer.MAX_VALUE;
            } else if (starting == ending) {                                                        //variable can only be a certain value
                if (control == ending) break;
                System.out.println("Value must be equal to " + starting);
            } else if (ending < starting || control > ending || control < starting) {               //variable out of bound
                System.out.println("Value must be in range of " + (int) starting + " to " + (int) ending + ".");
            } else
                break;

            System.out.print("Re-enter value: "); control = scan.nextDouble();                      //force user to enter new value
        }
        return control;
    }

    public static double currentWeight = 0;                                 //use global variable for avalible weight

    public static void begin() {//welcome message
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }

    public static double midTerm(Scanner scan, int isFinal) {               //get midterm score, also final score since they are basically the same
        switch(isFinal){        //switch title
            case 0: System.out.println("\nMidterm:"); break;
            case 1: System.out.println("\nFinal:");   break;
            //noDefaultNeeded
        }
        
        System.out.print("Weight (0-100)? "); double weight = inRange(scan.nextDouble(), 1, 100 - currentWeight, scan); currentWeight += weight;
        System.out.print("Score earned? ");   double score = inRange(scan.nextDouble(), 1, 100, scan);
        System.out.print("Were scores shifted (1=yes, 2=no)? "); 
        
        switch ((int) inRange(scan.nextInt(), 1, 2, scan)) {                //max score after shift can only be up to 100
            case 1: System.out.print("Shift amount? "); 
                    score += inRange(scan.nextDouble(), 0, -1, scan);       //shift can be as big as possible
                    if(score > 100) score = 100;                            //but score will be limited to 100
                    break;

            case 2: break;
            //noDefaultNeeded
        }

        double result = Math.round((score * weight / 100.0) * 10.0) / 10.0; //round up to first decimal

        System.out.println("Total points = " + (int) score + " / 100");
        System.out.println("Weighted score = " + result + " / " + (int) weight);
        return result;
    }

    public static double finalTerm(Scanner scan) {return midTerm(scan, 1);} //reuse midterm calc with different title

    public static double homework(Scanner scan) {//to return homework points
        System.out.println("\nHomework:");

        System.out.print("Weight (0-100)? ");
        double weight = inRange(scan.nextDouble(), 100 - currentWeight, 100 - currentWeight, scan); //making sure that weight stays in the 100% limit
        currentWeight += weight;
        
        System.out.print("Number of assignments? "); double asmCount = inRange(scan.nextInt(), 1, 0, scan);
        scan.nextLine();                                        // consume leftover line https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo

        double[] scores = new double[2];                        // scores[0] = student score; scores[1] = max score
        scores[1] = 30;                                         // max score without hw = max attendance score

        for (int i = 1; i <= asmCount; i++) {
            for(;;) {                                           //only exit if conditions are met
                System.out.print("Assignment " + i + " score and max: ");

                int count = 0; double[] temp = new double[2];
                for (String s : scan.nextLine().split(" ")) {   //split string to variable
                    temp[count++] += Integer.parseInt(s);       //and convert to int
                }

                if (temp[0] < 0 || temp[0] > temp[1]) {
                    System.out.println("Assigment score must be higher than 0 and lower than max assignment score");
                    continue;
                } else {
                    scores[0] += temp[0];                       //add user scores
                    scores[1] += temp[1];                       //add max scores
                    break;                                      //allow exit
                }
            }
        }

        System.out.print("How many sections did you attend? "); double attended = inRange(scan.nextInt(), 0, 5, scan);
        System.out.println("Section points = " + (int) attended*5 + " / 30"); scores[0] += attended * 5;
        System.out.println("Total points = " + (int) scores[0] + " / " + (int) scores[1]);
        
        double result = Math.round((scores[0] * weight / scores[1]) * 10.0) / 10.0;//round up to first decimal

        System.out.println("Weighted score = " + result + " / " + (int) weight);
        
        return result;
    }

    public static void report(double midTermScore, double finalScore, double homeworkScore){
        double score = midTermScore + finalScore + homeworkScore;
        System.out.println("\nOverall percentage = " + score);
        System.out.print("Your grade will be at least: ");

        if (score >= 85) {                                                          //
            System.out.println("3.0");                                              //
        } else if (score >= 75) {                                                   //gpa
            System.out.println("2.0");                                              //
        } else if (score >= 60) {                                                   //prediction?
            System.out.println("1.0");                                              //
        } else                                                                      //
            System.out.println("0.0");                                              //

        System.out.println("\033[3m<<your custom grade message here>>\033[3m");     //italic, most shell should be able to handle this
    }

    public static void main(String[] args) {                                
        begin();                                                            
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);                 //double use dot instead of comma
        report(midTerm(scan, 0), finalTerm(scan), homework(scan));
    }
}