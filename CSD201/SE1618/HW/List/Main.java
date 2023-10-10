public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addToHead(1);
        list.addToHead(2);
        list.addToHead(3);
        list.addToHead(4);
        list.addToHead(5);
        list.addToHead(6);
        list.addToHead(7);
        list.addToHead(8);
        list.addToHead(9);
        list.addToHead(10);

        System.out.println("Count: " + list.count());
        System.out.print("Traverse: ");
        list.traverse();
        System.out.println();

        int value = 5;
        System.out.println("Search: " + list.search(value));
        System.out.println();

        value = 3;
        System.out.println("Delete After: " + list.search(value));
        list.deleteAfter(list.search(value));
        System.out.print("Traverse: ");
        list.traverse();
        System.out.println();

        System.out.println("Delete From Tail: " + list.deleteFromTail());
        System.out.print("Traverse: ");
        list.traverse();
        System.out.println();

        System.out.println("Delete From Head: " + list.deleteFromHead());
        System.out.print("Traverse: ");
        list.traverse();
        System.out.println();

        value = 7;
        System.out.println("Delete: " + value);
        list.dele(value);
        System.out.print("Traverse: ");
        list.traverse();
        System.out.println();

        value = 8;
        System.out.println("Delete: " + value);
        Node toDelete = list.search(value);
        list.dele(toDelete);
        System.out.print("Traverse: ");
        list.traverse();
        System.out.println();

        System.out.println("Sum: " + list.sum());
        System.out.println("Max: " + list.max());
        System.out.println("Min: " + list.min());
    }
}