public class MyTree {
    Node root;
    boolean useCondition = false;

    public MyTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Product product) {
        if (product.name.contains("abc") || product.quantity < 10) {
            return;
        }

        Node newNode = new Node(product);

        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root, parent = root;

        while (current != null) {
            if (product.code.compareTo(current.product.code) < 0) {
                parent = current;
                current = parent.left;
                continue;
            }

            if (product.code.compareTo(current.product.code) > 0) {
                parent = current;
                current = parent.right;
                continue;
            }

            if (product.code.equals(current.product.code)) {
                System.out.println("Product code already exists!");
                return;
            }
        }

        if (product.code.compareTo(parent.product.code) < 0) {
            parent.left = newNode;
        } else
            parent.right = newNode;
    }

    public double averagePrice() {
        if (isEmpty())
            return 0;
        return totalPrice(root) / count(root);
    }

    public double totalPrice(Node current) {
        if (current == null)
            return 0;

        double sum = 0;
        sum += current.product.price;

        sum += totalPrice(current.left);
        sum += totalPrice(current.right);

        return sum;
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

    public int level(Node current) {
        return level(root, current, 0);
    }

    private int level(Node node, Node current, int level) {
        if (node == null) {
            return -1;
        }
        if (node == current) {
            return level;
        }
        int leftLevel = level(node.left, current, level + 1);
        if (leftLevel != -1) {
            return leftLevel;
        }
        return level(node.right, current, level + 1);
    }

    public void traverse() {
        if (!isEmpty()) {
            traverse(root, averagePrice());
        }
    }

    public void traverse(Node current, double averagePrice) {
        if (current == null) {
            return;
        }

        traverse(current.left, averagePrice);
        // System.out.print(current.product.code + " ");
        if (current.product.price > averagePrice) {
            System.out.println(current.toString() + " | " + level(current));
        }
        traverse(current.right, averagePrice);
    }

    public int deleteMinQuantity() {
        int min = minQuantity(root, root.product.quantity);

        int preDelete = count(root);
        while (preOrder(root, min) == -1) {
            preOrder(root, min);
        }
        int postDelete = count(root);

        System.out.println("Deleted " + (preDelete - postDelete) + " products with the lowest " + min + " in quantity");

        return preDelete - postDelete;
    }

    public int preOrder(Node current, int quantity) {
        if (current == null) {
            return 0;
        }

        if (current.product.quantity == quantity) {
            deleteNode(current, null);
            return -1;
        }

        preOrder(current.left, quantity);
        preOrder(current.right, quantity);

        return 0;

    }

    public int minQuantity(Node current, int min) {

        if (current == null) {
            return -1;
        }

        int leftMin = min, rightMin = min, temp;

        if (current.product.quantity < min)
            min = current.product.quantity;

        temp = minQuantity(current.left, min);

        if (temp != -1) {
            leftMin = temp;
        }

        temp = minQuantity(current.right, min);

        if (temp != -1) {
            rightMin = temp;
        }

        if (rightMin < min) {
            return rightMin;
        }

        if (leftMin < min) {
            return leftMin;
        }

        return min;
    }

    public Node searchParent(Node current) {
        if (isEmpty() || current == null || current == root)
            return null;

        Node parent = root;
        Node previous = null;

        while (parent != current) {
            previous = parent;
            if (parent.product.code.compareTo(current.product.code) < 0) {
                parent = parent.right;
            } else {
                parent = parent.left;
            }
        }

        return previous;
    }

    public void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            Node successor = inorderSuccessor(toDelete);
            parent = searchParent(successor);
            toDelete.product = successor.product;
            deleteNode(successor, parent);
            return;
        }
        if (parent == null) {
            parent = searchParent(toDelete);
        }

        if (toDelete.left == null && toDelete.right == null) {
            if (parent.left == toDelete) {
                parent.left = null;
            } else
                parent.right = null;
            return;
        }

        if (toDelete.left != null || toDelete.right != null) {
            if (parent.left == toDelete) {
                if (toDelete.left != null) {
                    parent.left = toDelete.left;
                } else {
                    parent.left = toDelete.right;
                }
            } else {
                if (toDelete.right != null) {
                    parent.right = toDelete.right;
                } else {
                    parent.right = toDelete.left;
                }
            }
            return;
        }
    }

    public Node inorderSuccessor(Node current) {
        if (current == null) {
            return null;
        }

        Node successor = current.right;
        while (successor != null && successor.left != null) {
            successor = successor.left;
        }

        return successor;
    }

}