public class Main {

    static Tree tree = new Tree();

    public static void main(String[] args) {
        createData(2);

        System.out.print("Preorder: ");
        tree.preorder();
        System.out.println();

        System.out.println(tree.searchParent(tree.search(9)));

        System.out.println(tree.min());
        System.err.println(tree.max());

        System.out.println();
    }

    public static void createData(int choice) {
        switch (choice) {
            case 1: // 1, 2, 3, 4, 5, 6, 7
                tree.insert(1);
                tree.insert(2);
                tree.insert(3);
                tree.insert(4);
                tree.insert(5);
                tree.insert(6);
                tree.insert(7);
                break;

            case 2: // 15, 10, 20, 8, 12, 17, 25, 6, 11, 22, 27
                tree.insert(15);
                tree.insert(10);
                tree.insert(20);
                tree.insert(8);
                tree.insert(12);
                tree.insert(17);
                tree.insert(25);
                tree.insert(6);
                tree.insert(11);
                tree.insert(22);
                tree.insert(27);
                break;

            case 3:
                tree.insert(15);
                break;

            case 4: // 100, 75, 125, 65, 115, 60, 70, 110, 120
                tree.insert(100);
                tree.insert(75);
                tree.insert(125);
                tree.insert(65);
                tree.insert(115);
                tree.insert(60);
                tree.insert(70);
                tree.insert(110);
                tree.insert(120);
                break;

            default:
                createData(1);
                createData(2);
        }
    }
}
