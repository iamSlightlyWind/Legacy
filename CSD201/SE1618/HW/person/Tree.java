class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Node {
    Person data;
    Node left, right;
}

/*
 * Write a program in Java using Binary Search Tree data structure to manage
 * information about persons. Variables used to store information about a person
 * are:
 *  name - the name of a person (character String) , which is the key of the
 * tree.
 *  age - the age of a person (integer value).
 * 1. void insert(String xName, int xAge) - check if the first letter of xName
 * is 'B'
 * (i.e. xName.charAt(0) == 'B') then do nothing, otherwise insert new person
 * with name=xName, age=xAge to the tree.
 * 2. Save all elements having age < the average age of the tree in format
 * (name,
 * age) to the file “q2.txt” by post-order traverse.
 * 3. Calculate the height of the tree.
 * 4. Calculate the number of nodes of the tree.
 * 5. Delete the root of the tree by copying.
 * 6. Perform breadth-first traverse from the root and delete by copying the
 * second node having age >= the average age.
 * 7. Check if the root having non-empty left-son then rotate it to right about
 * its
 * left-son.
 * 8. Perform pre-order traverse from the root, rotate the third node having
 * nonempty right-son then rotate it to left about its right-son and display
 * the tree
 * to the output screen.
 * 9. Calculate balance factor of all nodes. Display all node with balance
 * factor by
 * breadth-first traverse.
 * 10.Check whether a given binary search tree is height balanced (AVL tree) or
 * not.
 * 11.Calculate level of all nodes. Display all node with level by breadth-first
 * traverse.
 * 12.Balance a binary search tree by simple balancing algorithm.
 * 13. Perform pre-order traverse from the root, find the first node p having
 * age >=
 * 10, if node p has parent f and p is the right child of f then rotate node f
 * to left
 * about its right-son and display the tree to the output screen; otherwise, do
 * nothing.
 * 14.Perform pre-order traverse from the root, find the first node having age
 * >=
 * 10, if that node has parent f then delete by copying node f; otherwise, do
 * nothing
 */

public class Tree {
    Node root;

    public Tree() {

    }

    public void insert(String xName, int xAge) {
        if (xName.charAt(0) == 'B') {
            return;
        }
        Person p = new Person(xName, xAge);
        Node newNode = new Node();
        newNode.data = p;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (xName.compareTo(current.data.name) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public int count(Node current) {
        int count = 0;

        if (current != null) {
            count++;
        } else
            return 0;

        count += count(current.left);
        count += count(current.right);

        return count;
    }

    public int height(Node current) {
        if (current == null)
            return 0;
        else {
            int leftHeight = height(current.left);
            int rightHeight = height(current.right);

            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }
    }

    

}