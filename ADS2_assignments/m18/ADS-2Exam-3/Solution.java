import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

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
     * Complexity for BinarySearchST is .
     * Complexity for TST is .
     *
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary(
                "/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    /**
     * Read File.
     *
     * @param      file  The file
     *
     * @return     { Array of Word - String }.
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { Binary Search ST }.
     */
    public static BinarySearchST<String, Integer> loadDictionary(final String
        file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String,
        Integer>();
        // your code goes here
        String[] words = toReadFile(file);
        for (int i = 0; i < words.length; i++) {
            if (!st.contains(words[i].toLowerCase())) {
                st.put(words[i].toLowerCase(), 1);
            } else {
                st.put(words[i].toLowerCase(), st.get(words[i].toLowerCase())
                 + 1);
            }
        }
        return st;
    }
}

/**
 * Class for t 9.
 */
class T9 {
    /**
     * TST.
     */
    private TST<Integer> tst;
    /**
     * Constructs the object.
     *
     * @param      st    { Binary Search Symbol Table }.
     */
    T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<Integer>();
        for (String word : st.keys()) {
            tst.put(word, st.get(word));
        }
    }

    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }

    /**
     * Gets the Potential Words.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { Iterable }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        HashMap<Integer, List<String>> map = new HashMap<Integer,
        List<String>>();
        List<String>[] list = new List[2 + 2 + 2 + 2 + 2 + 2];
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list[0] = list1;
        List<String> list2 = new ArrayList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list[1] = list2;
        List<String> list3 = new ArrayList<String>();
        list3.add("d");
        list3.add("e");
        list3.add("f");
        list[2] = list3;
        List<String> list4 = new ArrayList<String>();
        list4.add("g");
        list4.add("h");
        list4.add("i");
        list[2 + 1] = list4;
        List<String> list5 = new ArrayList<String>();
        list5.add("j");
        list5.add("k");
        list5.add("l");
        list[2 + 2] = list5;
        List<String> list6 = new ArrayList<String>();
        list6.add("m");
        list6.add("n");
        list6.add("o");
        list[2 + 2 + 1] = list6;
        List<String> list7 = new ArrayList<String>();
        list7.add("p");
        list7.add("q");
        list7.add("r");
        list7.add("s");
        list[2 + 2 + 2] = list7;
        List<String> list8 = new ArrayList<String>();
        list8.add("t");
        list8.add("u");
        list8.add("v");
        list[2 + 2 + 2 + 1] = list8;
        List<String> list9 = new ArrayList<String>();
        list9.add("w");
        list9.add("x");
        list9.add("y");
        list9.add("z");
        list[2 + 2 + 2 + 2] = list9;
        List<String> list10 = new ArrayList<String>();
        list10.add("*");
        list[2 + 2 + 2 + 2 + 1] = list10;
        List<String> list11 = new ArrayList<String>();
        list11.add(" ");
        list[2 + 2 + 2 + 2 + 2] = list11;
        List<String> list12 = new ArrayList<String>();
        list12.add("#");
        list[2 + 2 + 2 + 2 + 2 + 1] = list12;

        for (int i = 1; i < 2 + 2 + 2 + 2 + 2 + 2 + 1; i++) {
            map.put(i, list[i]);
        }

        return null;
    }

    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      kf      { Top kf }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
        final int kf) {
        int k = kf;
        // your code goes here
        BinarySearchST<Integer, String> bst = new BinarySearchST<Integer,
        String>();
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        Queue<String> queue = new Queue<String>();
        for (String word : words) {
            int count = 0;
            String name = "";
            for (String prefix : getAllWords(word)) {
                count++;
            }
            pq.insert(count);
            if (!bst.contains(count)) {
                bst.put(count, word);
            } else if (bst.contains(count)) {
                name = bst.get(count);
                if (name.length() < word.length()) {
                    bst.put(count, word);
                }
            }
        }
        String[] arr = new String[k];
        while (k > 0) {
            int num = pq.delMax();
            arr[k - 1] = bst.get(num);
            k--;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            queue.enqueue(arr[i]);
        }
        return queue;
    }

    /**
     * Gives t9 Signature and top k Possibilities.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { Iterable }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
