public class Main {
    static MyTree tree = new MyTree();

    public static void main(String[] args) {
        createData(1);

        System.out.println();
        tree.deleteMinQuantity();

        System.out.println();
        System.out.println("Average price: " + tree.averagePrice());

        System.out.println();
        System.out.println("Traversing: ");
        tree.traverse();
    }

    public static void createData(int choice) {
        switch (choice) {
            case 1:
                tree.insert(new Product("P10", "Miliket", 30, 5.2));
                tree.insert(new Product("P05", "Apple", 31, 4.3));
                tree.insert(new Product("P15", "Sugar", 15, 5.1));
                tree.insert(new Product("P07", "Rose", 33, 5.4));
                tree.insert(new Product("P02", "Beer", 15, 3.2));
                tree.insert(new Product("P04", "Book", 35, 5.2));
                tree.insert(new Product("P03", "Milk", 36, 5.1));
                tree.insert(new Product("P01", "Bread", 37, 4.3));
                tree.insert(new Product("P12", "Rice", 38, 4.9));
                tree.insert(new Product("P20", "Candy", 39, 4.8));
                tree.insert(new Product("P11", "Coke", 40, 4.7));
                tree.insert(new Product("P14", "Water", 41, 4.6));
                tree.insert(new Product("P25", "Tea", 42, 4.5));
                break;

            case 2:
                tree.insert(new Product("50", "Miliket", 15, 5.2));
                tree.insert(new Product("30", "Apple", 15, 4.3));
                tree.insert(new Product("70", "Sugar", 12, 25.1));
                tree.insert(new Product("20", "Rose", 15, 15.4));
                tree.insert(new Product("40", "Beer", 11, 12.2));
                tree.insert(new Product("60", "Book", 12, 5.2));
                tree.insert(new Product("80", "Milk", 18, 5.1));
                break;
        }

        // (15*5.2) + (15*4.3) + (12*25.1) + (15*15.4) + (11*12.2) + (12*5.2) + (18*5.1)
        // + (16*4.3) + (14*4.9) + (13*4.8) + (12*4.7) + (11*4.6) + (10*4.5)
        // = 1314.9

        // 5.2 + 4.3 + 25.1 + 15.4 + 12.2 + 5.2 + 5.1 + 4.3 + 4.9 + 4.8 + 4.7 + 4.6 +
        // 4.5
        // = 100.3

        // 10, 05, 15, 07, 02
        // inorder: 1, 2, 3, 4, 5, 7, 10, 11, 12, 14, 15, 20, 25
        // preorder: 10, 5, 2, 1, 4, 3, 7, 15, 12, 11, 14, 20, 25
        // postorder: 1, 3, 4, 2, 7, 5, 11, 14, 12, 25, 20, 15, 10
    }
}