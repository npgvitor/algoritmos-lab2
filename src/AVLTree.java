public class AVLTree {
    private Node root;


    public void insert(int userId) {
        root = insert(root, userId);
    }

    private Node insert(Node node, int userId) {

        if (node == null)
            return new Node(userId);

        if (userId < node.userId)
            node.left = insert(node.left, userId);
        else if (userId > node.userId)
            node.right = insert(node.right, userId);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        // Esquerda-Esquerda
        if (balance > 1 && userId < node.left.userId)
            return rotateRight(node);

        // Direita-Direita
        if (balance < -1 && userId > node.right.userId)
            return rotateLeft(node);

        // Esquerda-Direita
        if (balance > 1 && userId > node.left.userId) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Direita-Esquerda
        if (balance < -1 && userId < node.right.userId) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {

        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {

        Node y = x.right;

        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
        }
    }

}
