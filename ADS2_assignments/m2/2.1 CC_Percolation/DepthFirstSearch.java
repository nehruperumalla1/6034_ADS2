/**
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

/**
 * Class for depth first paths.
 */
class DepthFirstSearch {
  /**
   * { var_description }.
   */
  private boolean[] marked;
  /**
   * { item_description }.
   */// marked[v] = is there an s-v path?
  private int[] edgeTo;
  /**
   * { item_description }.
   *edgeTo[v] = last edge on s-v path
   */
  /**
   * { var_description }.
   */
  private final int s;         // source vertex

  /**
   * Computes a path between {@code s} and .
   * @param g the graph
   * @param source the source vertex
   * @throws IllegalArgumentException unless {@code 0 <= s < V}
   */
  DepthFirstSearch(final Graph g, final int source) {
    this.s = source;
    edgeTo = new int[g.V()];
    marked = new boolean[g.V()];
    validateVertex(s);
    dfs(g, s);
  }

  // depth first search from v

  /**
   * { function_description }.
   *
   * @param      g     { parameter_description }
   * @param      v     { parameter_description }
   */
  private void dfs(final Graph g, final int v) {
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(g, w);
      }
    }
  }

  /**
   * Is there a path between the source vertex.
   * @param v the vertex
   * @return {@code true} if there is a path, {@code false} otherwise
   * @throws IllegalArgumentException unless {@code 0 <= v < V}
   */
  public boolean hasPathTo(final int v) {
    validateVertex(v);
    return marked[v];
  }
  /**
   * { Checking the Vertex }.
   *
   * @param      v     { Vertex }
   */
  private void validateVertex(final int v) {
    int vert = marked.length;
    if (v < 0 || v >= vert) {
      throw new IllegalArgumentException("vertex " + v
                                         + " is not between 0 and "
                                         + (vert - 1));
    }
  }
}