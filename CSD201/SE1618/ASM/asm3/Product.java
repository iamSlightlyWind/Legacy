public class Product {
    String type;
    String name;
    int quantity;
    double price;

    public Product(String type, String name, int quantity, double price) {
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String toString() {
        return type + " | " + name + " | " + quantity + " | " + price;
    }
}