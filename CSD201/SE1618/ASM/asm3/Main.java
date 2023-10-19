public class Main {
    static MyTree tree = new MyTree();

    public static void main(String[] args) {
        createData();

        System.out.println("minPrice: " + tree.minPrice());
        System.out.println();

        System.out.println("Traverse with condition: ");
        System.out.println("Average price: " + tree.averagePrice());
        tree.traverse();

        System.out.println();
        tree.deleteMinPrice();
        System.out.println();

        System.out.println("Traverse with condition: ");
        System.out.println("Average price: " + tree.averagePrice());
        tree.traverse();

    }

    public static void createData() {
        tree.insert(new Product("P10", "Miliket", 15, 5.2));
        tree.insert(new Product("P05", "Apple", 15, 4.3));
        tree.insert(new Product("P15", "Sugar", 12, 25.1));
        tree.insert(new Product("P07", "Rose", 15, 15.4));
        tree.insert(new Product("P02", "Beer", 11, 12.2));
        tree.insert(new Product("P04", "Book", 12, 5.2));
        tree.insert(new Product("P03", "Milk", 18, 5.1));
        tree.insert(new Product("P01", "Bread", 16, 4.3));
        tree.insert(new Product("P12", "Rice", 14, 4.9));
        tree.insert(new Product("P20", "Candy", 13, 4.8));
        tree.insert(new Product("P11", "Coke", 12, 4.7));
        tree.insert(new Product("P14", "Water", 11, 4.6));
        tree.insert(new Product("P25", "Tea", 10, 4.5));
    }
}