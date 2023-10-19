public class MyTree {
    Node root;

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

        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (product.type.compareTo(current.product.type) < 0) {
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

    public double averagePrice() {
        return sum(root) / count(root);
    }

    public int count(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 1;
        int leftCount = count(node.left);
        int rightCount = count(node.right);
        return count + leftCount + rightCount;
    }

    public double sum(Node node) {
        if (node == null) {
            return 0;
        }
        double sum = node.product.price;
        double leftSum = sum(node.left);
        double rightSum = sum(node.right);
        return sum + leftSum + rightSum;
    }

    public Node searchParent(Node current) {
        if (isEmpty() || current == null || current == root)
            return null;

        Node parent = root;
        Node prev = null;

        while (parent != current) {
            prev = parent;
            if (parent.product.type.compareTo(current.product.type) < 0) {
                parent = parent.right;
            } else {
                parent = parent.left;
            }
        }

        return prev;
    }

    public int level(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + level(searchParent(node));
    }

    public void traverse() {
        System.out.println("Traverse tree:");
        traverse(root);
    }

    public void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.left);
        traverse(node.right);
        if (node.product.price >= averagePrice()) {
            System.out.println(node.product + " | " + (level(node) - 1));
        }
    }

    public void traverse(int a) {
        System.out.println("Traverse tree:");
        traverse(root, 1);
    }

    public void traverse(Node node, int a) {
        if (node == null) {
            return;
        }
        traverse(node.left, 1);
        traverse(node.right, 1);
        System.out.println(node.product);
    }

    public double minPrice() {
        return minPrice(root);
    }

    public double minPrice(Node node) {
        if (node == null) {
            return Double.MAX_VALUE;
        }
        double min = node.product.price;
        double leftMin = minPrice(node.left);
        double rightMin = minPrice(node.right);
        if (leftMin < min) {
            min = leftMin;
        }
        if (rightMin < min) {
            min = rightMin;
        }
        return min;
    }

    public void deleteMinPrice() {
        System.out.println("Delete min price: ");
        deleteMinPrice(root, minPrice());
    }

    public void deleteMinPrice(Node node, double min) { // delete it, not print it
        if (node == null) {
            return;
        }
        deleteMinPrice(node.left, min);
        deleteMinPrice(node.right, min);
        if (node.product.price == min) {
            System.out.println(node.product);
            delete(node);
        }
    }

    public void delete(Node toDelete) {

        if (toDelete == null) {
            return;
        }

        Node parent = searchParent(toDelete);

        if(toDelete.left == null && toDelete.right == null){
            if(parent.left == toDelete){
                parent.left = null;
            } else {
                parent.right = null;
            }
            return;
        }

        if (toDelete.left != null && toDelete.right != null) {
            if(parent.left == toDelete){
                parent.left = null;
            } else {
                parent.right = null;
            }
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
    
}