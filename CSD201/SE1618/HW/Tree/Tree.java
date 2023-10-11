/* Write a Java program to implement a binary search tree of integer values with the following operations:
1.   boolean isEmpty() -   return true if a tree is empty, return false otherwise.
2.   void clear() - clear a tree.
3.   Node search(int x) - Search a node having value x. Return a reference to that node if found, return null otherwise.
4.   void insert(int x) - check if the key x does not exists in a tree then insert new node with value x into the tree.
5.   void breadth() - traverse a tree.
6.   void preorder(Node p) - recursive preorder traverse of a tree.
7.   void inorder(Node p) - recursive inorder traverse of a tree.
8.   void postorder(Node p) - recursive postorder traverse of a tree.
9.   int count() - count and return number of nodes in the tree.
10. void dele(int x) - delete a node having value x.
11. Node min() - find and return the node with minimum value in the tree. 
12. Node max() - find and return the node with maximum value in the tree. 
13. int sum() - return the sum of all values in the tree. 
14. int avg() - return the average of all values in the tree.
15. The height of a tree is the maximum number of nodes on a path from the root to a leaf node. Write a  function that returns the height of a binary tree.
16. The cost of a path in a tree is sum of the keys of the nodes participating  in that path. Write a  function that returns the cost of the most expensive  path from the root to a leaf node.
17. Write a  function to determine whether a given binary tree is AVL or not.
18. What value does the following function return when called with each of the binary trees in question 3?
int mystery(Node x) {
if (x == null)
return 0;
else
return max(mystery(x.left), mystery(x.right));
}
19. Write a  function to determine whether a given binary tree is a heap. */

public class Tree {
    Node root;

    public Tree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public void insert(int x) {
        Node newNode = new Node(x);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;

            if (x < current.data) {
                current = current.left;

                if (current == null) {
                    parent.left = newNode;
                    newNode.parent = parent;
                    return;
                }
            } else {
                current = current.right;

                if (current == null) {
                    parent.right = newNode;
                    newNode.parent = parent;
                    return;
                }
            }
        }
    }

    public Node search(int x) {
        Node current = root;

        while (current != null) {
            if (current.data == x) {
                return current;
            }
            if (current.data < x) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

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

        System.out.println(tree.search(22));
    }
}

class Node {
    int data;
    Node left;
    Node right;
    Node parent;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public String toString(){
        return "Parent: " + parent.data + " | Value: " + data;
    }
}

//Q: What is the easiest traversal method?
//