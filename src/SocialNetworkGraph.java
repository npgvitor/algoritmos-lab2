import java.util.ArrayList;
import java.util.List;

public class SocialNetworkGraph {

    private final List<User> users;
    private final List<List<Integer>> adjacencyList;

    public SocialNetworkGraph(int numberOfUsers) {

        users = new ArrayList<>();
        adjacencyList = new ArrayList<>();

        for (int i = 0; i < numberOfUsers; i++) {
            users.add(new User(i));
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addFriendship(int user1, int user2) {

        if (user1 == user2)
            return;

        if (!adjacencyList.get(user1).contains(user2)) {

            adjacencyList.get(user1).add(user2);
            adjacencyList.get(user2).add(user1);
        }
    }

    public int influenceDegree(int userId) {
        return adjacencyList.get(userId).size();
    }

    public void printGraph() {

        for (User user : users) {

            System.out.print("Usuário " + user.getId() + " -> ");

            for (Integer friend : adjacencyList.get(user.getId())) {
                System.out.print(friend + " ");
            }

            System.out.println();
        }
    }
}