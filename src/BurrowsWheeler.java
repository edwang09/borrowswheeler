import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        for (int i = 0; i < s.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            BinaryStdOut.write(s.charAt((csa.index(i) + s.length() - 1) % s.length()));
        }
        BinaryStdOut.close();

    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        MinPQ<Character> pq = new MinPQ<>();
        ST<Character, Queue<Integer>> st = new ST<>();
        int first = BinaryStdIn.readInt();
        int i = 0;
        // construct sorted and queue
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            pq.insert(c);
            if (st.contains(c)) {
                st.get(c).enqueue(i);
            } else {
                Queue<Integer> queue = new Queue<>();
                queue.enqueue(i);
                st.put(c, queue);
            }
            i++;
        }
        //construct next
        int size = pq.size();
        int[] next = new int[size];
        char[] ar = new char[size];

        for (int j = 0; j < size; j++) {
            char c = pq.delMin();
            ar[j] = c;
            next[j] = st.get(c).dequeue();
        }
        // build string
        BinaryStdOut.write(ar[first]);
        for (int j = next[first]; j != first; j = next[j]) {
            BinaryStdOut.write(ar[j]);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");

    }

}
