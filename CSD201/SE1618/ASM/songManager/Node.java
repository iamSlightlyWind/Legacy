public class Node {
    String name, artist;
    double rating;
    Node next;

    Node(String name, String artist, double rating) {
        this.name = name;
        this.artist = artist;
        this.rating = rating;
        this.next = null;
    }
}