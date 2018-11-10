import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Method.
     * Space Complexity for Graph is V + E.
     * Time Complexity for Adding Edge is 1.
     * Time Complexity for Dijkstra is E log V.
     * Worst Case Complexity for finding path is E.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        //The Whole Space Complexity of Graphs is V + E.
        // Because in worst case it may take all edges to a vertex.
        EdgeWeightedGraph edgeweighted = new EdgeWeightedGraph(vertices);
        int edges = Integer.parseInt(sc.nextLine());
        String[] input = null;
        int vertex1 = 0;
        int vertex2 = 0;
        double weight = 0;
        for (int i = 0; i < edges; i++) {
            input = sc.nextLine().split(" ");
            vertex1 = Integer.parseInt(input[0]);
            vertex2 = Integer.parseInt(input[1]);
            weight = Integer.parseInt(input[2]);
            if (vertex1 != vertex2) {
                // Adding Edge time Complexity is O(1).
                // Through index we can add edge in linear time.
                edgeweighted.addEdge(new Edge(vertex1, vertex2, weight));
            }
        }
        String caseToGo = sc.nextLine();
        switch (caseToGo) {
        case "Graph":
            // Time Complexity is V + E.
            // Because it should iterate all over the graph.
            System.out.println(edgeweighted);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] tokens = sc.nextLine().split(" ");
            int source = Integer.parseInt(tokens[0]);
            int destination = Integer.parseInt(tokens[1]);
            // Complexity for Dijkstra's is E log V.
            DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(
            edgeweighted, source);
            if (dijkstra.hasPathTo(destination)) {
                System.out.println(dijkstra.distTo(destination));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            String[] token = sc.nextLine().split(" ");
            int sour = Integer.parseInt(token[0]);
            int via = Integer.parseInt(token[1]);
            int dest = Integer.parseInt(token[2]);
            DijkstraUndirectedSP dijkstrasp = new DijkstraUndirectedSP(
            edgeweighted, sour);
            DijkstraUndirectedSP dijkstrasp1 = new DijkstraUndirectedSP(
            edgeweighted, via);
            if (dijkstrasp.hasPathTo(via) && dijkstrasp1.hasPathTo(dest)) {
                double distance = dijkstrasp.distTo(via) + dijkstrasp1.distTo(
                    dest);
                System.out.println(distance);
                String output = "";
                int ether = 0;
                int other = 0;
                // In Worst Case Complexity is E.
                // Because it should check path for destination which may
                // connected from edge to other.
                for (int path : dijkstrasp.pathTo(via)) {
                    output += path + " ";
                }
                for (int path1 : dijkstrasp1.pathTo(dest)) {
                    output += path1 + " ";
                }
                output += dest;
                System.out.println(output);
            } else {
                System.out.println("No Path Found.");
            }
            break;
        default:
            break;
        }
    }
}
