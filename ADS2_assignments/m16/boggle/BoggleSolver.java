import java.util.HashSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * Marked Array.
     */
    private boolean[][] marked;
    /**
     * Rows.
     */
    private int rows;
    /**
     * Cols.
     */
    private int cols;
    /**
     * TST Data Structure bject.
     */
    private TST<Integer> tst;
    /**
     * Count.
     */
    private int count;
    /**
     * Queue Object.
     */
    private Queue<String> queue;
    /**
     * Set of String Type.
     */
    private HashSet<String> set;
    /**
     * Constructs the object.
     * Time COmplexity is O(N).
     * N - Dictionary Length.
     *
     * @param      dictionary  The dictionary
     */
    BoggleSolver(final String[] dictionary) {
        count = 0;
        set = new HashSet<String>();
        tst = new TST<Integer>();
        for (String word : dictionary) {
            // map.put(word, false);
            tst.put(word, count++);
        }
        queue = new Queue<String>();
    }

    /**
     * Uses the DFS.
     * Time COmplexity is O(rows * cols).
     *
     * @param      board  The board
     */
    private void check(final BoggleBoard board) {
        rows = board.rows();
        cols = board.cols();
        marked = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                marked = new boolean[rows][cols];
                dfs("" + board.getLetter(i, j), board, i, j);
            }
            marked = new boolean[rows][cols];
        }
    }

    /**
     * Returns the score of string based on length.
     * Complexity is O(1).
     * @param      str   The string
     *
     * @return     { returns Score }.
     */
    public int scoreOf(final String str) {
        if (str.length() >= 0 && str.length() <= 2) {
            return 0;
        } else if (str.length() > 2 && str.length() <= 2 + 2) {
            return 1;
        } else if (str.length() == 2 + 2 + 1) {
            return 2;
        } else if (str.length() == 2 + 2 + 2) {
            return 2 + 1;
        } else if (str.length() == 2 + 2 + 2 + 1) {
            return 2 + 2 + 1;
        } else if (str.length() >= 2 + 2 + 2 + 2) {
            return 2 + 2 + 2 + 2 + 2 + 1;
        }
        return 0;
    }

    /**
     * { Checks the Index value with in matrix range or not }.
     * Complexity is O(1).
     *
     * @param      i     { index i }
     * @param      j     { index j }
     *
     * @return     { True if in range else false. }
     */
    private boolean check(final int i, final int j) {
        return (i >= 0 && i < rows && j >= 0 && j < cols);
    }

    /**
     * Determines if it has prefix.
     * Complexity is O(1).
     *
     * @param      str   The string
     *
     * @return     True if has prefix, False otherwise.
     */
    private boolean hasPrefix(final String str) {
        return tst.keyWithPrefix(str);
    }

    /**
     * Determines if word.
     * Complexity is O(tst size).
     *
     * @param      str   The string
     *
     * @return     True if word, False otherwise.
     */
    private boolean isWord(final String str) {
        return (tst.get(str) != null);
    }


    /**
     * DFS.
     * Time Complexity is O(Vertices + Edges).
     *
     * @param      str1   The string
     * @param      b     { Board }
     * @param      s     { Row }
     * @param      m     { Column }
     */
    private void dfs(final String str1, final BoggleBoard b, final int s,
        final int m) {
        // System.out.println(str);
        String str = str1;
        if (check(s, m)) {
            if (str.charAt(str.length() - 1) == 'Q') {
                    str += "U";
            }
            // System.out.println(str);
            marked[s][m] = true;
            if (str.length() > 2 && isWord(str)) {
                // System.out.println(str);
                set.add(str);
            }
        if (check(s - 1, m - 1) && !marked[s - 1][m - 1] && hasPrefix(str)) {
            // System.out.println(str);
            // marked[s][m] = true;
            dfs(str + b.getLetter(s - 1, m - 1), b, s - 1, m - 1);
            marked[s - 1][m - 1] = false;
        }
        if (check(s - 1, m) && !marked[s - 1][m] && hasPrefix(str)) {
            // System.out.println("hai");
            // marked[s][m] = true;
            dfs(str + b.getLetter(s - 1, m), b, s - 1, m);
            marked[s - 1][m] = false;
        }
        if (check(s - 1, m + 1) && !marked[s - 1][m + 1] && hasPrefix(str)) {
            // System.out.println("Hai");
            // marked[s][m] = true;
            dfs(str + b.getLetter(s - 1, m + 1), b, s - 1, m + 1);
            marked[s - 1][m + 1] = false;
        }
        if (check(s, m - 1) && !marked[s][m - 1] && hasPrefix(str)) {
            // marked[s][m] = true;
            dfs(str + b.getLetter(s, m - 1), b, s, m - 1);
            marked[s][m - 1] = false;
        }
        if (check(s, m + 1) && !marked[s][m + 1] && hasPrefix(str)) {
            // marked[s][m] = true;
            dfs(str + b.getLetter(s, m + 1), b, s, m + 1);
            marked[s][m + 1] = false;
        }
        if (check(s + 1, m - 1) && !marked[s + 1][m - 1] && hasPrefix(str)) {
            // marked[s][m] = true;
            dfs(str + b.getLetter(s + 1, m - 1), b, s + 1, m - 1);
            marked[s + 1][m - 1] = false;
        }
        if (check(s + 1, m + 1) && !marked[s + 1][m + 1] && hasPrefix(str)) {
            dfs(str + b.getLetter(s + 1, m + 1), b, s + 1, m + 1);
            marked[s + 1][m + 1] = false;
        }
        if (check(s + 1, m) && !marked[s + 1][m] && hasPrefix(str)) {
            dfs(str + b.getLetter(s + 1, m), b, s + 1, m);
            marked[s + 1][m] = false;
        }
        }
    }
    /**
     * Gets all valid words.
     * Returns the set of all valid words in the given Boggle board, as an
     * Iterable.
     * Complexity is O(rows * Cols).
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        check(board);
        return set;
    }
}
