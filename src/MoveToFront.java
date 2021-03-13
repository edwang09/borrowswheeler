import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    // alphabet size of extended ASCII
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] codeTable = new char[R];
        char[] positionTable = new char[R];
        for (int i = 0; i < R; i++) {
            codeTable[i] = (char) i;
            positionTable[i] = (char) i;
        }
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        for (char c : input) {
            BinaryStdOut.write(positionTable[c]);
            for (int i = positionTable[c]; i > 0; i--) {
                codeTable[i] = codeTable[i - 1];
                positionTable[codeTable[i - 1]] += 1;
            }
            codeTable[0] = c;
            positionTable[c] = 0;
        }

        // close output stream
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] codeTable = new char[R];
        for (int i = 0; i < R; i++) {
            codeTable[i] = (char) i;
        }

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(codeTable[c]);
            char code = codeTable[c];
            for (int i = c; i > 0; i--) {
                codeTable[i] = codeTable[i - 1];
            }
            codeTable[0] = code;
        }

        // close output stream
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }

}
