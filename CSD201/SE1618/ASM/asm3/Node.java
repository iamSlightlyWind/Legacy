public class Node {
    Product product;
    Node left, right;

    public Node(Product product) {
        this.product = product;
        left = right = null;
    }
}