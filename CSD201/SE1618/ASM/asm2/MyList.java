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
        if(index == 0){
            head = new Node(new Student(xName, xGPA));
            head.next = current;
            return;
        }

        for (int i = 0; i < index - 1; i++) {
            if (current.next == null)
                return;
            current = current.next;
        }

        Node newNode = new Node(new Student(xName, xGPA));
        newNode.next = current.next;
        current.next = newNode;
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