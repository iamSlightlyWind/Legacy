public class LinkedList {
    /*
     * Write a Java program to implement a singly linked list of integer values with
     * the following operations:
     * 1. void addToHead(int x) - add a node with value x at the head of a list.
     * 2. void addToTail(int x) - add a node with value x at the tail of a list.
     * 3. void addAfter(Node p, int x) - add a node with value x after the node p.
     * 4. void traverse() - traverse from head to tail and dislay info of all nodes
     * in the list.
     * 5. int deleteFromHead() - delete the head and return its info.
     * 6. int deleteFromTail() - delete the tail and return its info.
     * 7. int deleteAter(Node p) - delete the node after the node p and return its
     * info.
     * 8. void dele(int x) - delele the first node whose info is equal to x.
     * 9. Node search(int x) - search and return the reference to the first node
     * having info x.
     * 10. int count() - count and return number of nodes in the list.
     * 11. void delete(int i) - delete an i-th node on the list. Besure that such a
     * node exists.
     * 12. void sort() - sort the list by ascending order of info.
     * 13. void dele(Node p) - delete node p if it exists in the list.
     * 14. int [] toArray() - create and return array containing info of all nodes
     * in the list.
     * 15. Merge two ordered singly linked lists of integers into one ordered list.
     * 16. void addBefore(Node p, int x) - add a node with value x before the node
     * p.
     * 17. Attach a singly linked list to the end of another singly linked list.
     * 18. int max() - find and return the maximum value in the list.
     * 19. int min() - find and return the minimum value in the list.
     * 20. int sum() - return the sum of all values in the list.
     * 21. int avg() - return the average of all values in the list.
     * 22. boolean sorted() - check and return true if the list is sorted, return
     * false if the list is not sorted.
     * 23. void insert(int x) - insert node with value x into sorted list so that
     * the new list is sorted.
     * 24. Reverse a singly linked list using only one pass through the list.
     * 25. Check whether two singly linked list have the same contents.
     */

    Node head, tail;

    public LinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void addToHead(int x) {
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            Node q = new Node(x);
            q.next = head;
            head = q;
        }
    }

    public void addToTail(int x) {
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            tail.next = new Node(x);
            tail = tail.next;
        }
    }

    public void addAfter(Node p, int x) {
        Node q = new Node(x);
        q.next = p.next;
        p.next = q;
        if (p == tail) {
            tail = q;
        }
    }

    public void traverse() {
        Node p = head;
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    public int deleteFromHead() {
        if (isEmpty()) {
            return -1;
        }
        int value = head.value;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        return value;
    }

    public int deleteFromTail() {
        if (isEmpty()) {
            return -1;
        }
        int value = tail.value;
        if (head == tail) {
            head = tail = null;
        } else {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            tail = p;
            tail.next = null;
        }
        return value;
    }

    public int deleteAfter(Node p) {
        if (p == null || p.next == null) {
            return -1;
        }
        int value = p.next.value;
        p.next = p.next.next;
        return value;
    }

    public void dele(Node p) {
        if (p == null) {
            return;
        }
        if (p == head) {
            deleteFromHead();
        } else {
            Node q = head;
            while (q.next != p) {
                q = q.next;
            }
            if (q.next != null) {
                deleteAfter(q);
            }
        }
    }

    public int max() {
        if (isEmpty()) {
            return -1;
        }
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

    public int min() {
        if (isEmpty()) {
            return -1;
        }
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

    public int sum() {
        if (isEmpty()) {
            return -1;
        }
        int sum = 0;
        Node p = head;
        while (p != null) {
            sum += p.value;
            p = p.next;
        }
        return sum;
    }

    public void dele(int x) {
        if (isEmpty()) {
            return;
        }
        if (head.value == x) {
            deleteFromHead();
        } else {
            Node p = head;
            while (p.next != null && p.next.value != x) {
                p = p.next;
            }
            if (p.next != null) {
                deleteAfter(p);
            }
        }
    }

    public Node search(int x) {
        if (isEmpty()) {
            return null;
        }

        Node p = head;
        while (p != null && p.value != x) {
            p = p.next;
        }
        if (p == null) {
            return null;
        } else if (p.value == x) {
            return p;
        } else
            return null;
    }

    public void delete(int i) {
        if (isEmpty()) {
            return;
        }
        if (i == 0) {
            deleteFromHead();
        } else {
            Node p = head;
            int count = 0;
            while (p.next != null && count != i - 1) {
                p = p.next;
                count++;
            }
            if (p.next != null) {
                deleteAfter(p);
            }
        }
    }

    public int count() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }
}