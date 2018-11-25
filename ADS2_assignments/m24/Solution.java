import java.util.*;
public class Solution {
    public static void main(String[] args) {
        BinarySearchST binary = new BinarySearchST<Integer, Integer>();
        SequentialSearchST seq = new SequentialSearchST<Integer, Integer>();
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < max; i++) {
            binary.put(i, i);
            seq.put(i, i);
            int search = i + 1;
            binary.get(search);
            // seq.get(i,i);
            // System.out.println("lol");
            if (i == binary.countt * 10) {
                System.out.println("10 times faster when - " + i + " " + binary.countt);
            }
            if (i == binary.countt * 100) {
                System.out.println("100 times faster when - " + i + " " + binary.countt);
            }
            if (i == binary.countt * 1000) {
                System.out.println("1000 times faster when - " + i + " " + binary.countt);
                // break;
            }
            for (int j = 0; j < i; j++) {
                binary.get(j);
                seq.get(j);
                if (seq.count == binary.countt * 10) {
                    System.out.println(j + "--- For 10");
                    continue;
                }
                if (seq.count == binary.countt * 100) {
                    System.out.println(j + "--- For 100");
                    continue;
                }
                if (seq.count == binary.countt * 1000) {
                    System.out.println(j + "--- For 1000");
                    break;
                }
            }
        }
    }
}
