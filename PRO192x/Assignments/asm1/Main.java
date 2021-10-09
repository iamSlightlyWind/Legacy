import java.util.Random;
import java.util.Scanner;

public class Main{

    static int guessCount = 0;  //
    static int gameCount = 0;   //global vars to better manage statistic
    static int bestGuess = 0;   //

    public static boolean keepGame(Scanner scan){   //func to check if user want to continue playing
        System.out.print("Do you want to play again? ");
        String inp = scan.next(); inp = inp.toUpperCase(); //quote: không phân biệt hoa thường trong câu trả lời “yes”
        System.out.println("");

        if(inp.equals("YES") || inp.equals("Y")){
            return true;
        }return false;
    }

    public static void start(Random rand, Scanner scan){    //game func
        int luckyNumber = rand.nextInt(100);
        System.out.println("I'm thinking of a number between 0 and 100...");

        int input = 0;                  //
        int localGuessCount = 1;        //local vars

        while(input != luckyNumber){    //keep asking for user input until input = lucky number
            System.out.print("Your guess? ");
            input = scan.nextInt();
            if(check(luckyNumber,input) == true){   //if match, print result
                System.out.println("You got it right in " + localGuessCount + " guesses!");
            }else localGuessCount++;
        }

        guessCount += localGuessCount;
        gameCount++;

        if(gameCount == 1){ //if first game, best count = first count
            bestGuess = localGuessCount;
        }else if(bestGuess > localGuessCount){  //change best count if condition match
            bestGuess = localGuessCount;
        }

        if(keepGame(scan)){ //if return true <=> keep playing
            start(rand,scan);
        }
    }

    public static boolean check(int ln, int input){ //compare latest input with "lucky number"
        if(input != ln){
            if(input > ln) System.out.println("It's lower.");
            if(input < ln) System.out.println("It's higher.");
            return false;
        }else return true;
    }

    public static void main(String[] args){
        Random myRand = new Random();                   //
        Scanner myScanner = new Scanner(System.in);     //single random and scanner obj for every uses
        start(myRand,myScanner);
        System.out.println("Overall results:");
        System.out.println("Total games   = " + gameCount);
        System.out.println("Total gyesses = " + guessCount);
        System.out.println("Guesses/game  = " + (double) guessCount / (double) gameCount);
        System.out.println("Best game     = " + bestGuess);
    }
}