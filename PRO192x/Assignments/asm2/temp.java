import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("file");
        File out = new File("out");
        String myString = "";

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                myString = myString.concat(scan.nextLine() + "\n");
            }
            System.out.println(myString);
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(out);
            writer.write(myString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}