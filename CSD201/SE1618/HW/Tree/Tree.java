/* Write a Java program to implement a binary search tree of integer values with the following operations:
1.   x boolean isEmpty() -   return true if a tree is empty, return false otherwise.
2.   x void clear() - clear a tree.
3.   x Node search(int x) - Search a node having value x. Return a reference to that node if found, return null otherwise.
4.   x void insert(int x) - check if the key x does not exists in a tree then insert new node with value x into the tree.
5.   void breadth() - traverse a tree.
6.   x void preorder(Node p) - recursive preorder traverse of a tree.
7.   void inorder(Node p) - recursive inorder traverse of a tree.
8.   void postorder(Node p) - recursive postorder traverse of a tree.
9.   x int count() - count and return number of nodes in the tree.
10.  x void dele(int x) - delete a node having value x.
11.  x Node min() - find and return the node with minimum value in the tree. 
12.  0x Node max() - find and return the node with maximum value in the tree. 
13.  int sum() - return the sum of all values in the tree. 
14.  int avg() - return the average of all values in the tree.
15.  The height of a tree is the maximum number of nodes on a path from the root to a leaf node. Write a  function that returns the height of a binary tree.
16.  The cost of a path in a tree is sum of the keys of the nodes participating  in that path. Write a  function that returns the cost of the most expensive  path from the root to a leaf node.
17.  Write a  function to determine whether a given binary tree is AVL or not.
18.  What value does the following function return when called with each of the binary trees in question 3?
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

    public int sum() {
        if (isEmpty()) {
            return 0;
        }

        int sum = root.data;

        sum += sum(root);

        return 0;
    }

    public int sum(Node current) {
        if(current == null){
            return 0;
        }
        
        int sum = 0;
        
        if(current.left != null){
            sum = sum(current.left);
        }

        if(current.right != null){
        sum = sum(current.left);
        }

        
        return 0;
    }

    public Node min() {
        if (isEmpty())
            return null;

        Node current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public Node max() {
        if (isEmpty())
            return null;

        Node current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (current.data < value) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        if (parent.data < value) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    public Node search(int value) {
        if (isEmpty())
            return null;

        Node current = root;
        while (current != null) {
            if (current.data != value) {
                if (current.data < value) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
        }

        return current;
    }

    public Node searchParent(Node current) {
        if (isEmpty() || current == null || current == root)
            return null;

        Node parent = root;
        Node prev = null;

        while (parent != current) {
            prev = parent;
            if (parent.data < current.data) {
                parent = parent.right;
            } else {
                parent = parent.left;
            }
        }

        return prev;
    }

    public void preorder() {
        if (isEmpty())
            return;

        printPreorder(root);
    }

    public void printPreorder(Node current) {
        if (current != null) {
            System.out.print(current);
        } else
            return;

        if (current.left != null) {
            printPreorder(current.left);
        }

        if (current.right != null) {
            printPreorder(current.right);
        }
    }

    public void delete(int value) {
        Node toDelete = search(value);

        if (toDelete == null) {
            return;
        }

        Node parent = searchParent(toDelete);

        if (toDelete.left != null && toDelete.right != null) {
            return;
        }

        if (toDelete.left != null || toDelete.right != null) {
            if (parent.left == toDelete) {
                if (toDelete.left != null) {
                    parent.left = toDelete.left;
                    // toDelete.left.parent = parent;
                } else {
                    parent.left = toDelete.right;
                    // toDelete.right.parent = parent;
                }
            } else {
                if (toDelete.right != null) {
                    parent.right = toDelete.right;
                    // toDelete.right.parent = parent;
                } else {
                    parent.right = toDelete.left;
                    // toDelete.left.parent = parent;
                }
            }
            return;
        }

        if (toDelete.left == null && toDelete.right == null) {
            if (parent.left == toDelete) {
                parent.left = null;
            } else
                parent.right = null;
            return;
        }
    }

    public int count() {
        if (isEmpty()) {
            return 0;
        } else
            return count(root) + 1;
    }

    public int count(Node current) {
        if (current == null) {
            return 0;
        }

        int count = 0;

        if (current.left != null) {
            count += 1 + count(current.left);
        }

        if (current.right != null) {
            count += 1 + count(current.right);
        }

        return count;
    }

}