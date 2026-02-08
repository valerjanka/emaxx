package com.val.algos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Benchmark {

    static StringTokenizer tok;
    static String line;
    static BufferedReader in;

    // The method to benchmark (original with trim)
    private static String nextWithTrim() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            line = in.readLine();
            if (line == null) return null;
            tok = new StringTokenizer(line.trim());
        }
        return tok.nextToken();
    }

    // The method to benchmark (optimized without trim)
    private static String nextWithoutTrim() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            line = in.readLine();
            if (line == null) return null;
            tok = new StringTokenizer(line);
        }
        return tok.nextToken();
    }

    public static void main(String[] args) throws IOException {
        // Generate a large input string
        StringBuilder sb = new StringBuilder();
        // Many lines with leading/trailing spaces
        // Use enough lines to make the benchmark last at least a few seconds
        int numLines = 500000;
        for (int i = 0; i < numLines; i++) {
            sb.append("   Token1 Token2 Token3   \n");
        }
        String input = sb.toString();

        // Warmup
        System.out.println("Warming up...");
        for (int i = 0; i < 5; i++) {
            runTest(input, true);
            runTest(input, false);
        }

        // Measurement
        long totalTimeWithTrim = 0;
        long totalTimeWithoutTrim = 0;
        int iterations = 10;

        System.out.println("Starting benchmark...");

        for (int i = 0; i < iterations; i++) {
            System.out.print("Iteration " + (i + 1) + ": ");
            long t1 = runTest(input, true);
            System.out.print("Trim=" + (t1 / 1_000_000.0) + "ms, ");
            totalTimeWithTrim += t1;

            long t2 = runTest(input, false);
            System.out.println("NoTrim=" + (t2 / 1_000_000.0) + "ms");
            totalTimeWithoutTrim += t2;
        }

        System.out.println("\nResults:");
        System.out.println("Average time with trim: " + (totalTimeWithTrim / iterations) / 1_000_000.0 + " ms");
        System.out.println("Average time without trim: " + (totalTimeWithoutTrim / iterations) / 1_000_000.0 + " ms");

        double improvement = (double)(totalTimeWithTrim - totalTimeWithoutTrim) / totalTimeWithTrim * 100;
        System.out.printf("Improvement: %.2f%%\n", improvement);
    }

    private static long runTest(String input, boolean useTrim) throws IOException {
        in = new BufferedReader(new StringReader(input));
        tok = null;
        line = null;

        long start = System.nanoTime();
        while (true) {
            String token;
            if (useTrim) {
                token = nextWithTrim();
            } else {
                token = nextWithoutTrim();
            }
            if (token == null) break;
        }
        long end = System.nanoTime();
        return end - start;
    }
}
