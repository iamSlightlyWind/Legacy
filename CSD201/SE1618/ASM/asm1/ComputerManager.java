public class ComputerManager {
    Node root;

    public ComputerManager() {
        root = null;
    }

    public Node find(String key) {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (!current.data.equals(key)) {
            if (key.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current == null) {
                return null;
            }
        }

        return current;
    }

    public void delete(String key) {

        Node toDelete = find(key);

        if (toDelete == null) {
            return;
        }

        if (toDelete.parent != null) {
            if (toDelete == toDelete.parent.left) {
                toDelete.parent.left = null;
            } else {
                toDelete.parent.right = null;
            }
        } else {
            root = null;
        }
    }

    public void printInOrder() {
        inOrder(root);
    }

    public void inOrder(Node current) {
        if (current != null) {
            inOrder(current.left);
            System.out.println(current.com.toString());
            inOrder(current.right);
        }
    }

    public void printPreOrder() {
        preOrder(root);
    }

    public void preOrder(Node current) {
        if (current != null) {
            System.out.println(current.com.toString());
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void insert(Node node) {
        if (root == null) {
            root = node;
            return;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;

            if (node.data.compareTo(current.data) < 0) {
                current = current.left;

                if (current == null) {
                    parent.left = node;
                    node.parent = parent;
                    return;
                }
            } else {
                current = current.right;

                if (current == null) {
                    parent.right = node;
                    node.parent = parent;
                    return;
                }
            }
        }
    }
}