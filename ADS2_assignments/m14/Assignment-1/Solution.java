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
     * Complexity for Storing the Strings is N M.
     * N - No.of Strings
     * M - Length of Each String.
     *
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        TST<Integer> tst = new TST<Integer>();
        int val = 0;
        for (int i = 0; i < words.length; i++) {
            tst.put(words[i], val++);
            // System.out.println(tst.get(words[i]));
            for (int j = 0; j < words[i].length(); j++) {
                tst.put(words[i].substring(j, words[i].length()), val++);
            }
        }
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        for (String st : tst.keysWithPrefix(str)) {
            // System.out.println(tst.get(st));
            System.out.println(st);
        }
    }
    /**
     * Loads words.
     *
     * @return     { returns Loaded Words - String Array }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
