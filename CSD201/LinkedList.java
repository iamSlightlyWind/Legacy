public class LinkedList {
    public class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    Node head;
    Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    // void addToHead(int x) - add a node with value x  at the head of  a list.
    public void addToHead(int x) {
        Node newNode = new Node(x);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // void addToHead(int x) - add a node with value x  at the head of  a list.
    public void addToTail(int x) {
        Node newNode = new Node(x);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // void addAfter(Node p, int x) - add a node with value x  after the node p
    public void addAfter(Node p, int x) {
        Node newNode = new Node(x);
        p.next = newNode;
        newNode.next = p.next;
    }

    // void traverse() - traverse from head to tail and dislay info of all nodes in
    // the list.
    public void traverse() {
        Node p = head;
        while (p != null) {
            System.out.println(p.value);
            p = p.next;
        }
    }

    // int deleteFromHead() - delete the head and return its info.
    public int deleteFromHead() {
        int value = head.value;
        head = head.next;
        return value;
    }

    // int deleteFromTail() - delete the tail and return its info.
    public int deleteFromTail() {
        int value = tail.value;
        Node p = head;
        while (p.next != tail) {
            p = p.next;
        }
        tail = p;
        tail.next = null;
        return value;
    }

    // int deleteAter(Node p) - delete the node after the node  p  and return its
    // info.
    public int deleteAfter(Node p) {
        int value = p.next.value;
        p.next = p.next.next;
        return value;
    }

    // void dele(int x) - delele the first node whose info is equal to  x.
    public void dele(int x) {
        Node p = head;
        while (p.next != null) {
            if (p.next.value == x) {
                p.next = p.next.next;
                return;
            }
            p = p.next;
        }
    }

    // Node search(int x) - search and return the reference to the first node having
    // info x.
    public Node search(int x) {
        Node p = head;
        while (p != null) {
            if (p.value == x) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // int count() - count and return number of nodes in the list.
    public int count() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    // void dele(int i) - delete an i-th node on the list. Besure that such a node
    // exists.
    public void delete(int i) {
        if (i == 0) {
            head = head.next;
            return;
        }
        Node p = head;
        for (int j = 0; j < i - 1; j++) {
            p = p.next;
        }
        p.next = p.next.next;
    }

    // void sort() - sort the list by ascending order of info.
    public void sort() {
        Node p = head;
        while (p != null) {
            Node q = p.next;
            while (q != null) {
                if (p.value > q.value) {
                    int temp = p.value;
                    p.value = q.value;
                    q.value = temp;
                }
                q = q.next;
            }
            p = p.next;
        }
    }

    // int [] toArray() - create and return array containing info of all nodes in
    // the list.
    public int[] toArray() {
        int[] arr = new int[count()];
        Node p = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = p.value;
            p = p.next;
        }
        return arr;
    }

    // void addBefore(Node p, int x) - add a node with value x  before the node p.
    public void addBefore(Node p, int x) {
        Node newNode = new Node(x);
        Node q = head;
        while (q.next != p) {
            q = q.next;
        }
        q.next = newNode;
        newNode.next = p;
    }

    // Attach a singly linked list to the end of another singly linked list.
    public void attach(LinkedList list) {
        tail.next = list.head;
        tail = list.tail;
    }

    // int max() - find and return the maximum value in the list. 
    public int max() {
        int max = head.value;
        Node p = head;
        while (p != null) {
            if (p.value > max) {
                max = p.value;
            }
            p = p.next;
        }
        return max;
    }

    // int min() - find and return the minimum value in the list. 
    public int min() {
        int min = head.value;
        Node p = head;
        while (p != null) {
            if (p.value < min) {
                min = p.value;
            }
            p = p.next;
        }
        return min;
    }

    // int sum() - return the sum of all values in the list. 
    public int sum() {
        int sum = 0;
        Node p = head;
        while (p != null) {
            sum += p.value;
            p = p.next;
        }
        return sum;
    }

    // int avg() - return the average of all values in the list.
    public int avg() {
        return sum() / count();
    }

    // boolean sorted() - check and return true if the list is sorted, return false
    // if the list is not sorted.
    public boolean sorted() {
        Node p = head;
        while (p.next != null) {
            if (p.value > p.next.value) {
                return false;
            }
            p = p.next;
        }
        return true;
    }
    

}