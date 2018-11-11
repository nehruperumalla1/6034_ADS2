import java.util.Scanner;
/**
 * Class for edge.
 */
class Edge {
    /**
     * Either vertex.
     */
    private int either;
    /**
     * Other vertex.
     */
    private int other;
    /**
     * Weight of both either and other.
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      ver1   The vertex 1
     * @param      ver2   The vertex 2
     * @param      weigh  The weigh
     */
    Edge(final int ver1, final int ver2, final double weigh) {
        either = ver1;
        other = ver2;
        weight = weigh;
    }

    /**
     * { Either Vertex. }
     *
     * @return     { Returns Either Vertex }.
     */
    public int either() {
        return either;
    }

    /**
     * Other Vertex.
     *
     * @param      v     { Vertex }
     *
     * @return     { Returns vertex other than v }
     */
    public int other(final int v) {
        if (v == either) {
            return other;
        }
        return either;
    }

    /**
     * Weight of Edge.
     *
     * @return     { Reeturns Weight of Edge - double }
     */
    public double weight() {
        return this.weight;
    }
}

/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     * Bag Object Array.
     */
    private Bag<Edge>[] bag;
    /**
     * Vertices.
     */
    private int v;
    /**
     * Edges.
     */
    private int e;
    /**
     * Constructs the object.
     * Complexity is O(V).
     *
     * @param      ver   The version
     */
    EdgeWeightedGraph(final int ver) {
        bag = new Bag[ver];
        for (int i = 0; i < ver; i++) {
            bag[i] = new Bag<Edge>();
        }
        v = ver;
        e = 0;
    }

    /**
     * Adds an edge.
     *
     * @param      edge  The edge
     */
    public void addEdge(final Edge edge) {
        int vertex = edge.either();
        int w = edge.other(vertex);
        bag[vertex].add(edge);
        bag[w].add(edge);
        e++;
    }

    /**
     * No.of Vertices.
     *
     * @return     { Vertices Count - int }
     */
    public int v() {
        return v;
    }

    /**
     * No.of Edges.
     *
     * @return     { Edge Count - int }
     */
    public int e() {
        return e;
    }

    /**
     * { Iterable of Edges }.
     *
     * @param      vertex  The vertex
     *
     * @return     { Returns Iterable }
     */
    public Iterable<Edge> adj(final int vertex) {
        return bag[vertex];
    }
}

/**
 * Class for dijkstra sp.
 */
class DijkstraSP {
    /**
     * { Edge Weighted Undirected Graph }.
     */
    private EdgeWeightedGraph graph;
    /**
     * Source.
     */
    private int source;
    /**
     * Edge To Array.
     */
    private Edge[] edgeTo;
    /**
     * Distance To array.
     */
    private double[] distTo;
    /**
     * Priority Queue.
     */
    private IndexMinPQ<Double> pq;
    /**
     * Constructs the object.
     * Complexity is O(E + V).
     *
     * @param      g     { Edge Weighted Undirected Graph }
     * @param      s     { Source }
     */
    DijkstraSP(final EdgeWeightedGraph g, final int s) {
        graph = g;
        source = s;
        edgeTo = new Edge[graph.v()];
        distTo = new double[graph.v()];
        pq = new IndexMinPQ<Double>(graph.v());
        for (int i = 0; i < graph.v(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq.insert(source, 0.0);
        distTo[s] = 0.0;
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : graph.adj(v)) {
                relax(e, v);
            }
        }
    }

    /**
     * It is used to relax the vertices that has created a path.
     * Complexity is O(logE).
     *
     * @param      e     { Edge }
     * @param      v     { Vertex }
     */
    public void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
                // System.out.println("j ");
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * Determines if it has path to.
     * Complexity is O(1).
     *
     * @param      v     { Vertex }
     *
     * @return     True if has path to, False otherwise.
     */
    public boolean hasPathTo(final int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Distance to given Vertex.
     * Complexity is O(1).
     * @param      v     { Vertex }
     *
     * @return     { Returns Double Distance }
     */
    public double distTo(final int v) {
        return distTo[v];
    }

    /**
     * { Gives the Iterable of Edges adjcent to it. }
     * Complexity is O(ElogV).
     *
     * @param      v     { Vertex }
     *
     * @return     { Iterable. }
     */
    public Iterable<Edge> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }

    /**
     * Finding the Shortest Distance between Source and Given Vertex.
     * Complexity is O(E).
     * @param      vertex  The vertex
     *
     * @return     { Returns the Distance }
     */
    public double distance(final int vertex) {
        int distance = 0;
        for (Edge e : pathTo(vertex)) {
            distance += e.weight();
        }
        return distance;
    }
}
/**
 * Class Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Method.
     * Time Complexity is O(V + E).
     * For Dijkstra's Algo it would check all Vetices and Edges.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int vertices = Integer.parseInt(input[0]);
        int edges = Integer.parseInt(input[1]);
        String[] stations = sc.nextLine().split(" ");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] connection = sc.nextLine().split(" ");
            int vertex1 = checkPos(stations, connection[0]);
            int vertex2 = checkPos(stations, connection[1]);
            if (vertex1 != -1 && vertex2 != -1) {
                graph.addEdge(new Edge(vertex1, vertex2,
                    Double.parseDouble(connection[2])));
            }
        }
        int queriesCount = Integer.parseInt(sc.nextLine());
        String[] tokens = null;
        DijkstraSP shortest = null;
        for (int i = 0; i < queriesCount; i++) {
            tokens = sc.nextLine().split(" ");
            int pos1 = checkPos(stations, tokens[0]);
            int pos2 = checkPos(stations, tokens[1]);
            // System.out.println(pos1 + " -- " + pos2);
            shortest = new DijkstraSP(graph, pos1);
            System.out.println((int) shortest.distance(pos2));
        }
    }

    /**
     * Check Position to check the index pos of given String.
     * Complexity is O(V).
     *
     * @param      stations  The stations
     * @param      statio   The station
     *
     * @return     { Returns Position of String }
     */
    public static int checkPos(final String[] stations, final String statio) {
        for (int j = 0; j < stations.length; j++) {
            if (statio.equals(stations[j])) {
                return j;
            }
        }
        return -1;
    }
}
