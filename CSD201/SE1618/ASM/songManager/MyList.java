public class MyList {
    Node head;

    MyList() {
        head = null;
    }

    public void add(String xName, String xArtist, double xRating) {
        if (xName.contains("aba") || xRating < 2)
            return;

        Node newNode = new Node(xName, xArtist, xRating);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
    }

    public void display() {
        display(head);
    }

    public void display(Node current) {
        if (current == null) {
            return;
        } else
            System.out.println(toString(current));
        display(current.next);
    }

    public String toString(Node current) {
        return current.name + ", by " + current.artist + ": " + current.rating;
    }

    public int isSorted() {
        if(head == null)
            return isSorted(head);
        else return 1;
    }

    public int isSorted(Node current) {
        if (current.next == null)
            return 1;  
        if (current.rating < current.next.rating)
            return 0;
        if (current.rating == current.next.rating && current.name.compareTo(current.next.name) > 0)
            return 0;
        return isSorted(current.next);
    }

    public int deleteLowestRating() {
        if (head == null)
            return 0;

        int count = 0;
        double min = getLowestRating();
        while (head.rating == min) {
            head = head.next;
            count++;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.rating == min) {
                current.next = current.next.next;
                count++;
            } else
                current = current.next;
        }

        return count;
    }

    public double getLowestRating() {
        if (head == null)
            return 0;
        double min = head.rating;

        Node current = head;
        while (current != null) {
            if (current.rating < min)
                min = current.rating;
            current = current.next;
        }

        return min;
    }
}