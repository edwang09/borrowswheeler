import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    private final int[] index;
    private final int size;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        size = s.length();
        index = new int[size];
        MinPQ<String> strings = new MinPQ<>();
        ST<String, Queue<Integer>> st = new ST<>();
        for (int i = 0; i < size; i++) {
//            StringBuilder sb = s.substring(i, s.length()) + s.substring(0,i);
            String cs = s.substring(i) + s.substring(0, i);
            strings.insert(cs);
            if (st.contains(cs)) {
                st.get(cs).enqueue(i);
            } else {
                Queue<Integer> queue = new Queue<>();
                queue.enqueue(i);
                st.put(cs, queue);
            }
        }
        for (int i = 0; i < size; i++) {
            index[i] = st.get(strings.delMin()).dequeue();
        }

    }

    // length of s
    public int length() {
        return size;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException();
        }
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        for (int i = 0; i < s.length(); i++) {
            StdOut.println(csa.index(i));
        }
    }
}
