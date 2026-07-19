import java.util.Random;

public class Main {

    public static void main(String[] args) {

        AVLTree users = new AVLTree();

        users.insert(30);
        users.insert(20);
        users.insert(10);
        users.insert(25);
        users.insert(40);
        users.insert(50);
        users.insert(5);

        System.out.println("IDs dos usuários em ordem:");
        users.inOrder();

        // Grafo pequeno
        SocialNetworkGraph graph = new SocialNetworkGraph(5);

        graph.addFriendship(0, 1);
        graph.addFriendship(0, 2);
        graph.addFriendship(1, 3);
        graph.addFriendship(2, 4);

        graph.printGraph();

        System.out.println("\nGrau de influência:");
        System.out.println("Usuário 0: " + graph.influenceDegree(0));
        System.out.println("Usuário 1: " + graph.influenceDegree(1));

        // Grafo aleatório
        SocialNetworkGraph network = new SocialNetworkGraph(1000);

        Random random = new Random();

        int edges = 0;

        while (edges < 5000) {

            int u = random.nextInt(1000);
            int v = random.nextInt(1000);

            int before = network.influenceDegree(u);

            network.addFriendship(u, v);

            if (network.influenceDegree(u) > before) {
                edges++;
            }
        }

        System.out.println("\nInfluência de alguns usuários:");

        int[] usersTest = {10, 50, 100, 500, 900};

        for (int id : usersTest) {
            System.out.println(
                    "Usuário " + id +
                            " possui " +
                            network.influenceDegree(id) +
                            " amigos.");
        }
    }
}