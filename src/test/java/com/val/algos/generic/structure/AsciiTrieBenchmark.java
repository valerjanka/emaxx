package com.val.algos.generic.structure;

import java.util.Random;

public class AsciiTrieBenchmark {
    public static void main(String[] args) {
        AsciiTrie trie = new AsciiTrie();
        Random random = new Random(42);
        int wordCount = 5000;
        int wordLength = 10;

        System.out.println("Generating " + wordCount + " words of length " + wordLength + "...");
        for (int i = 0; i < wordCount; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < wordLength; j++) {
                char c = (char) ('a' + random.nextInt(26));
                sb.append(c);
            }
            trie.addWord(sb.toString());
        }

        System.out.println("Warming up...");
        for (int i = 0; i < 5; i++) {
            trie.toString();
        }

        System.out.println("Benchmarking...");
        long totalTime = 0;
        int iterations = 10;
        for (int i = 0; i < iterations; i++) {
            long startTime = System.nanoTime();
            String s = trie.toString();
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            System.out.println("Iteration " + (i + 1) + ": " + (endTime - startTime) / 1_000_000.0 + " ms");
        }

        System.out.println("Average time: " + (totalTime / iterations) / 1_000_000.0 + " ms");
        System.out.println("Output length: " + trie.toString().length());
    }
}
