import java.util.*;

public class Graph {

    private int vertices;
    private LinkedList<Edge>[] adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList[source].add(new Edge(destination, weight));
    }

    public void dijkstra(int start) {

        int[] distance = new int[vertices];
        boolean[] visited = new boolean[vertices];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for (int i = 0; i < vertices - 1; i++) {

            int minVertex = -1;

            for (int j = 0; j < vertices; j++) {
                if (!visited[j] &&
                        (minVertex == -1 || distance[j] < distance[minVertex])) {
                    minVertex = j;
                }
            }

            visited[minVertex] = true;

            for (Edge edge : adjacencyList[minVertex]) {

                int newDist = distance[minVertex] + edge.weight;

                if (newDist < distance[edge.destination]) {
                    distance[edge.destination] = newDist;
                }
            }
        }

        System.out.println("Shortest distances from vertex " + start + ":");

        for (int i = 0; i < vertices; i++) {
            System.out.println("To vertex " + i + " = " + distance[i]);
        }
    }
}