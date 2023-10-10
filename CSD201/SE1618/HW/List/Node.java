public class Node {
    int value;
    Node next;

    Node(int x) {
        value = x;
        next = null;
    }

    public String toString() {
        return value + "";
    }
}