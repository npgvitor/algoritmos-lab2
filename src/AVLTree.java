public class AVLTree {
    private Node root;

    public void insert(int userId) {
        System.out.println("Inserindo: " + userId);
        root = insert(root, userId);
        System.out.println("Raiz atual: " + root.userId);
        System.out.println("-------------------");
    }

    private Node insert(Node node, int userId) {

        if (node == null) {
            System.out.println("Criando nó: " + userId);
            return new Node(userId);
        }

        if (userId < node.userId) {
            System.out.println(
                    userId + " < " + node.userId +
                            " => vai para ESQUERDA"
            );

            node.left = insert(node.left, userId);

        } else if (userId > node.userId) {
            System.out.println(
                    userId + " > " + node.userId +
                            " => vai para DIREITA"
            );

            node.right = insert(node.right, userId);

        } else {
            System.out.println("Valor duplicado: " + userId);
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        System.out.println(
                "Verificando equilíbrio do nó " +
                        node.userId +
                        " | Fator: " + balance
        );


        // LL
        if (balance > 1 && userId < node.left.userId) {
            System.out.println(
                    "Caso LL: rotação direita no nó " + node.userId
            );
            return rotateRight(node);
        }


        // RR
        if (balance < -1 && userId > node.right.userId) {
            System.out.println(
                    "Caso RR: rotação esquerda no nó " + node.userId
            );
            return rotateLeft(node);
        }


        // LR
        if (balance > 1 && userId > node.left.userId) {

            System.out.println(
                    "Caso LR: rotação esquerda em " +
                            node.left.userId +
                            " e depois direita em " +
                            node.userId
            );

            node.left = rotateLeft(node.left);

            return rotateRight(node);
        }


        // RL
        if (balance < -1 && userId < node.right.userId) {

            System.out.println(
                    "Caso RL: rotação direita em " +
                            node.right.userId +
                            " e depois esquerda em " +
                            node.userId
            );

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
        System.out.print("Percurso InOrder: ");
        inOrder(root);
        System.out.println();
    }


    private void inOrder(Node node) {

        if (node != null) {
            inOrder(node.left);

            System.out.print(node.userId + " ");

            inOrder(node.right);
        }
    }
}