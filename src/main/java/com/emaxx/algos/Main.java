package com.emaxx.algos;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

public class Main {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static String line;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            FenwickTree fenwickTree = new FenwickTree(1000_001);
            String action = next();
            long result = 0;
            while (action.charAt(0) != 'Q') {
                if (action.charAt(0) == 'B') {
                    int bid = (int) ((nextDouble() + 0.000000001) * 100);
                    fenwickTree.inc(bid, 1);
                } else if (action.charAt(0) == 'S') {
                    int bid = (int) ((nextDouble() + 0.000000001) * 100);
                    int amount = nextInt();
                    result += Math.min(amount, fenwickTree.sum(bid));
                } else { //DEL
                    int bid = (int) ((nextDouble() + 0.000000001) * 100);
                    fenwickTree.inc(bid, -1);
                }
                action = next();
            }
            out.printf("%.2f", result / 100.);
            in.close();
            out.close();

        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }


    /**
     * Reversed tree that calculates sum from to the end
     */
    static class FenwickTree {
        int[] tree;

        public FenwickTree(int n) {
            tree = new int[n];
        }

        public void inc(int position, int delta) {
            position = tree.length - position - 1;
            for (; position < tree.length; position = (position | (position + 1))) {
                tree[position] += delta;
            }
        }

        public int sum(int position) {
            position = tree.length - position - 1;
            int result = 0;
            for (; position >= 0; position = (position & (position + 1)) - 1) {
                result += tree[position];
            }
            return result;
        }
    }


    private static int nextInt() throws IOException {
        return parseInt(next());
    }

    private static long nextLong() throws IOException {
        return parseLong(next());
    }

    private static double nextDouble() throws IOException {
        return parseDouble(next());
    }

    private static String next() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            line = in.readLine();
            tok = new StringTokenizer(line.trim());
        }
        return tok.nextToken();
    }

    private static String nextLine() throws IOException {
        if (tok == null || !tok.hasMoreTokens()) {
            line = in.readLine();
        }
        return line;
    }

    private static boolean hasNext() throws Exception {
        if (tok == null || !tok.hasMoreTokens()) {
            line = in.readLine();
            if (line != null) {
                tok = new StringTokenizer(line.trim());
                return hasNext();
            } else {
                return false;
            }
        }
        return true;
    }
}
