import java.util.ArrayList;

public class Main {

    static MyList list = new MyList();

    public static void main(String[] args) {
        createData();
        list.display();

        double gpa = 5.6;

        System.out.println("Deleting first student with " + gpa + " gpa");
        list.delete(gpa);
        list.display();

        gpa = 7;
        System.out.println("First 5 students with gpa > " + gpa + ": ");
        displayFirst5(7);

        System.out.println();
        System.out.println("Second max:" + list.findMaxes(2).data.toString());
        System.out.println("Third max:" + list.findMaxes(3).data.toString());
        System.out.println();

        gpa = 8.2;
        System.out.println("Deleting last student with " + gpa + " gpa");
        list.deleteLast(gpa);
        list.display();

        System.out.println("Adding a student at index 5");
        list.add("new", 7.3, 5);
        list.display();

        gpa = 5;
        System.out.println("Last 5 students with gpa > " + gpa + ": ");
        displayLast5(gpa);
    }

    public static void createData() {
        list.addLast(new Node(new Student("A", 8.1)));
        list.addLast(new Node(new Student("B", 3.9)));
        list.addLast(new Node(new Student("C", 5.6)));
        list.addLast(new Node(new Student("D", 9.0)));
        list.addLast(new Node(new Student("E", 7.5)));
        list.addLast(new Node(new Student("F", 9.4)));
        list.addLast(new Node(new Student("G", 8.2)));
        list.addLast(new Node(new Student("I", 5.6)));
        list.addLast(new Node(new Student("J", 8.2)));
        list.addLast(new Node(new Student("K", 3.2)));
        list.addLast(new Node(new Student("L", 8.3)));
    }

    public static void displayFirst5(double gpa) {
        int displayCount = 0;
        Node current = list.head;
        while (current != null && displayCount < 5) {
            if (current.data.gpa > gpa) {
                System.out.println(current.data);
                displayCount++;
            }
            current = current.next;
        }
    }

    public static void displayLast5(double gpa) {
        ArrayList<Student> last = new ArrayList();
        Node current = list.head;

        while (current != null) {
            if (current.data.gpa > gpa) {
                last.add(current.data);
            }
            current = current.next;
        }

        for (int i = last.size() - 1; i > last.size() - 6 && i >= 0; i--) {
            System.out.println(last.get(i).toString());
        }
    }
}