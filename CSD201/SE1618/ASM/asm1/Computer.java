public class Computer {
    String brandName;
    int isBroken;
    double price, discountPercent;

    public Computer(String brandName, int isBroken, double price, double discountPercent) {
        this.brandName = brandName;
        this.isBroken = isBroken;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public String toString() {
        return brandName + ": " + price + " - " + (discountPercent * 100) + "%";
    }
}