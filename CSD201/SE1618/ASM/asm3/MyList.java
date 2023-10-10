public class MyList {
    Node head;

    public MyList() {
        head = null;
    }

    public void insert(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
    }

    public void add(String xName, double xGPA, int index) {
        Node current = head;
        for (int i = 1; i < index; i++) {
            if (current.next != null) {
                current = current.next;
            } else
                break;
        }

        Node next = current.next;
        current.next = new Node(new Student(xName, xGPA));
        current.next.next = next;
    }

    public void addLast(Node newNode) {
        if (newNode.data.name.charAt(0) == 'X' || newNode.data.gpa < 4.0)
            return;
        insert(newNode);
    }

    public Node findMaxes(int choice) {
        Node max = head;
        Node secondMax = head;
        Node thirdMax = head;
        Node current = head;
        while (current != null) {
            if (current.data.gpa > max.data.gpa) {
                thirdMax = secondMax;
                secondMax = max;
                max = current;
            } else if (current.data.gpa > secondMax.data.gpa) {
                thirdMax = secondMax;
                secondMax = current;
            } else if (current.data.gpa > thirdMax.data.gpa) {
                thirdMax = current;
            }
            current = current.next;
        }

        switch (choice) {
            case 2:
                return secondMax;
            case 3:
                return thirdMax;
        }
        return null;
    }

    public void delete(double gpa) {
        if (head == null)
            return;
        if (head.data.gpa == gpa) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.gpa == gpa) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public void delete(Node toDelete) {
        if (toDelete == head) {
            head = head.next;
            return;
        }

        Node current = head;

        while (current.next != toDelete) {
            current = current.next;
        }

        current.next = current.next.next;
    }

    public void deleteLast(double gpa) {
        if (head == null)
            return;

        Node current = head;
        Node last = head;
        while (current != null) {
            if (current.data.gpa == gpa) {
                last = current;
            }
            current = current.next;
        }

        delete(last);
    }

    public void display() {
        System.out.println("Displaying:");
        display(head);
        System.out.println();
    }

    public void display(Node current) {
        if (current == null)
            return;
        System.out.println(current.data.toString());
        display(current.next);
    }
}

/*
 * The MyList class is a linked list of Student objects (String name, double
 * gpa).
 * 
 * addLast(String xName, double xGPA) – check if xName has the first letter ‘X’
 * or xGPA < 4.0 then do nothing, otherwise add new student to the end of the
 * list.
 * Delete the first student having gpa = 5.6.
 * Display the first 5 students having gpa > 8.0. For example: (a, 8.0) (b,
 * 8.2), (c, 8.6), (d, 9.0) (e, 7.5) (f, 9.4) (g, 8.2) (h, 5.0)
 * Find the second max gpa. Display the first student having that gpa.
 * Sort the list descendingly by gpa.
 * Delete the last student having gpa = 7.0
 * Display the last 5 students having age > 5.6
 * Find the third max gpa.
 * add(String xName, double xGPA, int index) – insert the new student at the
 * given index (start from 0)
 * sort(int startIndex, int endIndex) – sort the linked list ascendingly by name
 * from startIndex to endIndex
 */

/*
 * public class Node {
 * Student data;
 * Node next;
 * 
 * public Node(Student data) {
 * this.data = data;
 * this.next = null;
 * }
 * }
 */

/*
 * public class Student {
 * public String name;
 * public double gpa;
 * 
 * public Student(String name, double gpa) {
 * this.name = name;
 * this.gpa = gpa;
 * }
 * 
 * public String toString() {
 * return "(" + name + ", " + gpa + ")";
 * }
 * }
 */