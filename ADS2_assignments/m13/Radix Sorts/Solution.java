import java.util.Scanner;
import java.util.Arrays;

// class Threeway{
//  private String[] str;
//  private int lo;
//  private int hi;
//  private int d;
//  Threeway(final String[] input) {
//      str = input;
//      lo = 0;
//      hi = str.length - 1;
//      d = 0;
//      sort(input, lo, hi, d);
//  }

//  public void sort(final String[] str, final int lo,
//  final int hi, final int d) {
//      if (hi <= lo) {
//          return ;
//      }
//      int lt = lo + 1, gt = hi;
//      int i = lo;
//      int v = charAt(str[i], d);
//      while (i <= gt) {
//          int t = charAt(str[lt], d);
//          if (t < v) {
//              System.out.println("Inside if 1");
//              exch(str, lt++, i++);
//          } else if (t > v) {
//              System.out.println("Inside if 2");
//              exch(str, i, gt--);
//          } else {
//              i++;
//          }
//      }
//      System.out.println(Arrays.toString(str));
//      sort(str, lo, lt - 1, d);
//      if (v >= 0) {
//          sort(str, lt, gt, d + 1);
//      }
//      sort(str, gt + 1, hi, d);
//  }

//  public void exch(final String[] str, final int i, final int j) {
//      String temp = str[i];
//      str[i] = str[j];
//      str[j] = temp;
//  }

//  public int charAt(final String str, final int pos) {
//      if (pos == str.length()) {
//          return -1;
//      } else {
//          return str.charAt(pos);
//      }
//  }

//  public void print() {
//      System.out.println(Arrays.toString(str));
//  }
// }

/**
 * Class for lsd.
 */
class Lsd {
    /**
     * Constructs the object.
     */
    Lsd() { }
    /**
     * Sorts the given strings.
     * Complxity is 2 N W.
     * N - No.of Strings.
     * W - Fixed Length of String.
     *
     * @param      a     { String array }
     * @param      w     { Fixed length of String }
     */
    public void sort(final String[] a, final int w) {
        int n = a.length;
        int s = (int) Math.pow(2 * 2, 2 * 2);   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[s + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < s; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
/**
 * Class for Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Method for Sorting the Given Strings.
     * Complexity is 2 N W.
     * N - No.of Strings.
     * W - Fixed Length of String.
     * It is Stable.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        String[] input = new String[count];
        for (int i = 0; i < count; i++) {
            input[i] = sc.nextLine();
        }
        // Threeway way = new Threeway(input);
        Lsd lsd = new Lsd();
        // way.print();
        lsd.sort(input, input[0].length());
    }
}
