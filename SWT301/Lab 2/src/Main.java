import manager.StudentManager;

public class Main {
    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        sm.populate();

        while (true) {
            sm.printMenu();
        }
    }
}
