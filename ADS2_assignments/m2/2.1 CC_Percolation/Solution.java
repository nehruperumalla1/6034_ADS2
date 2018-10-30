import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
	/**
	 * variable vertex.
	 */
	private int vertices;
	/**
	 * checks for percolation.
	 *
	 * @param      grid         The grid
	 * @param      objectGraph  The object graph
	 * @param      vertexOne    The vertex one
	 *
	 * @return     returns the boolean.
	 */
	public boolean percolation(final boolean[][] grid,
	                           final Graph graph,
	                           final int sourceVertex) {
		vertices = sourceVertex;
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (grid[i][j]) {
					int temp = simplify(i, j);
					if (i == 0) {
						graph.addEdge(
						    temp, vertices * vertices);
					}
					if (i == vertices - 1) {
						graph.addEdge(temp,
						                    vertices * vertices + 1);
					}
					if (i - 1 >= 0 && grid[i - 1][j]) {
						graph.addEdge(temp,
						                    simplify(i - 1, j));
					}
					if (i + 1 < vertices && grid[i + 1][j]) {
						graph.addEdge(
						    temp, simplify(i + 1, j));
					}
					if (j - 1 >= 0 && grid[i][j - 1]) {
						graph.addEdge(temp,
						                    simplify(i, j - 1));
					}
					if (j + 1 < vertices && grid[i][j + 1]) {
						graph.addEdge(
						    temp, simplify(i, j + 1));
					}

				}
			}
		}
		DepthFirstSearch objectDFP = new DepthFirstSearch(
		    graph, vertices * vertices);
		return objectDFP.hasPathTo(vertices * vertices + 1);
	}
	/**
	 * this method gives the edge of graph.
	 *
	 * Time complexity is O(1).
	 * @param      row     The row
	 * @param      column  The column
	 *
	 * @return      int
	 */
	public int simplify(final int row, final int column) {
		return row * vertices + column;
	}
}
/**
 * class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {

	}
	/**
	 * main function to handle testcases.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertex = Integer.parseInt(sc.nextLine());
		boolean[][] grid = new boolean[vertex][vertex];
		Percolation percobj = new Percolation();
		Graph graph = new Graph(vertex * vertex + 2);
		try {
			while (sc.hasNext()) {
				int row = sc.nextInt(), col = sc.nextInt();
				grid[row - 1][col - 1] = true;

			}
		} catch (Exception e) {

		} finally {
			System.out.println(percobj.percolation(
			                       grid, graph, vertex));
		}
	}
}
