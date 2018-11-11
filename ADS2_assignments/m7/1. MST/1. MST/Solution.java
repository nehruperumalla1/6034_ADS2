import java.util.Scanner;

/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * Vertex 1 - v.
     */
    private int vertex1;
    /**
     * Vertex 2 - w.
     */
    private int vertex2;
    /**
     * Weight of Edge.
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      v      { Vertex 1 }
     * @param      w      { Vertex 2 }
     * @param      weigh  The weigh
     */
    Edge(final int v, final int w, final double weigh) {
        this.vertex1 = v;
        this.vertex2 = w;
        this.weight = weigh;
    }

    /**
     * Either method to get Random Value - v.
     *
     * @return     { Returns v of type int }
     */
    public int either() {
        return vertex1;
    }

    /**
     * Returns Other than V.
     *
     * @param      v     { Vertex 1 }
     *
     * @return     { Returns vertex other than v of int type }
     */
    public int other(final int v) {
        if (v == vertex1) {
            return vertex2;
        }
        return vertex1;
    }

    /**
     * Compares Weights.
     *
     * @param      that  The that
     *
     * @return     { Returns int based on weights. }
     */
    public int compareTo(final Edge that) {
        if (weight < that.weight) {
            return -1;
        } else if (weight > that.weight) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns weight of edge.
     *
     * @return     { Returns weight of type double. }
     */
    public double weight() {
        return weight;
    }
}

/**
 * Class for weighted edge graph.
 */
class WeightedEdgeGraph {
    /**
     * Bag Array.
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
     *
     * @param      sca   The sca
     */
    WeightedEdgeGraph(final Scanner sca) {
        v = Integer.parseInt(sca.nextLine());
        e = Integer.parseInt(sca.nextLine());
        bag = new Bag[v];
        for (int i = 0; i < v; i++) {
            bag[i] = new Bag<Edge>();
        }
        Edge edge = null;
        int vx = 0;
        int w = 0;
        double weight = 0;
        String[] input = null;
        for (int i = 0; i < e; i++) {
            input = sca.nextLine().split(" ");
            vx = Integer.parseInt(input[0]);
            w = Integer.parseInt(input[1]);
            weight = Double.parseDouble(input[2]);
            edge = new Edge(vx, w, weight);
            addEdge(edge);
        }
    }

    /**
     * Gets the v.
     *
     * @return     The v.
     */
    public int getV() {
        return v;
    }

    /**
     * Adds an edge.
     *
     * @param      edge  The edge
     */
    public void addEdge(final Edge edge) {
        int ver = edge.either();
        int w = edge.other(ver);
        bag[ver].add(edge);
        bag[w].add(edge);
    }

    /**
     * Iterable.
     *
     * @param      verte     { Vertex }
     *
     * @return     { List }
     */
    public Iterable<Edge> adj(final int verte) {
        return bag[verte];
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph,
     * use foreach notation:
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int ve = 0; ve < getV(); ve++) {
            int selfLoops = 0;
            for (Edge ee : adj(ve)) {
                if (ee.other(ve) > ve) {
                    list.add(ee);
                } else if (ee.other(ve) == ve) {
                    if (selfLoops % 2 == 0) {
                        list.add(ee);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }
}

/**
 * Class for mst.
 */
class Mst {
    /**
     * Min PQ to Store Edges.
     */
    private MinPQ<Edge> pq = new MinPQ<Edge>();
    /**
     * Queue to stores edges of MST.
     */
    private Queue<Edge> mst = new Queue<Edge>();
    /**
     * Total Weight of MST.
     */
    private double weight = 0;
    /**
     * Constructs the object.
     *
     * @param      egraph  The egraph
     */
    Mst(final WeightedEdgeGraph egraph) {
        for (Edge e : egraph.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(egraph.getV());
        int v = 0;
        int w = 0;
        while (!pq.isEmpty() && mst.size() <= egraph.getV() - 1) {
            Edge e = pq.delMin();
            v = e.either();
            w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
        System.out.format("%.5f", weight);
    }

    /**
     * Gives the Weight of MST.
     *
     * @return     { Returns weight of type int }
     */
    public double weight() {
        return weight;
    }
}

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
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sca = new Scanner(System.in);
        Mst mst = new Mst(new WeightedEdgeGraph(sca));
    }
}
