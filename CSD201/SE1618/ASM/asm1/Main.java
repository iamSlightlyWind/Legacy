public class Main {
    static ComputerManager cm = new ComputerManager();

    public static void main(String[] args) {
        createData();

        cm.printInOrder();

        System.out.println();
        System.out.println();

        cm.printPreOrder();

        System.out.println();
        System.out.println();

        System.out.println(cm.find("Dell").com.toString());
        cm.delete("Dell");
        System.out.println(cm.find("Asus").com.toString()); // error since deleted

        System.out.println();
    }

    public static void createData() {
        cm.insert(new Node(new Computer("Dell", 0, 1000, 0.1)));
        cm.insert(new Node(new Computer("Apple", 0, 1000, 0.2)));
        cm.insert(new Node(new Computer("Lenovo", 0, 1000, 0.3)));
        cm.insert(new Node(new Computer("HP", 0, 1000, 0.15)));
        cm.insert(new Node(new Computer("Acer", 0, 1000, 0.25)));
        cm.insert(new Node(new Computer("Asus", 0, 1000, 0.35)));
        cm.insert(new Node(new Computer("Alienware", 0, 1000, 0.5)));
        cm.insert(new Node(new Computer("Razer", 0, 1000, 0.22)));
        cm.insert(new Node(new Computer("Microsoft", 0, 1000, 0.27)));
        cm.insert(new Node(new Computer("Samsung", 0, 1000, 0.17)));
    }
}
