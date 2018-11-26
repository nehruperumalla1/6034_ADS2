import java.util.Scanner;
// import java.util.*;
import java.util.ArrayList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    public static void transform() {
    	 	String s = BinaryStdIn.readString();
	        CircularSuffixArray csa = new CircularSuffixArray(s);
	        int first = 0;
	        while (first < csa.length() && csa.index(first) != 0) {
	            first++;
	        }
	        BinaryStdOut.write(first);
	        for (int i = 0; i < csa.length(); i++) {
	            BinaryStdOut.write(s.charAt((csa.index(i) + s.length() - 1) % s.length()));
	        }
	        BinaryStdOut.close();
    }

    public static void inverseTransform() {
    	// System.out.println("ramu");
    	// System.out.println("gangu");
    	int first = BinaryStdIn.readInt();

    	// System.out.println(first);
        String t = BinaryStdIn.readString();
        System.out.println(t);
        int n = t.length();
        int[] count = new int[256 + 1], next = new int[n];
        for (int i = 0; i < n; i++)
            count[t.charAt(i) + 1]++;
        for (int i = 1; i < 256 + 1; i++)
            count[i] += count[i - 1];
        for (int i = 0; i < n; i++)
            next[count[t.charAt(i)]++] = i;
        for (int i = next[first], c = 0; c < n; i = next[i], c++)
            BinaryStdOut.write(t.charAt(i));
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
    	if(args[0].equals("-")) {
           transform();
        } else {
            
            inverseTransform();
        }
    }
}
