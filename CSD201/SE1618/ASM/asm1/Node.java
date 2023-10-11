public class Node {
    Computer com;
    String data;
    Node left;
    Node right;
    Node parent;

    public Node(Computer com) {
        this.com = com;
        data = com.brandName;
        left = null;
        right = null;
        parent = null;
    }
}