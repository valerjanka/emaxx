package com.val.algos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class IntegrationTest {
    public static void main(String[] args) throws Exception {
        testSimpleFlow();
        testWhitespaceHandling();
        System.out.println("Integration tests passed!");
    }

    private static void testSimpleFlow() throws Exception {
        String input = "B 20.0\nS 10.0 5\nQ\n";
        String expectedOutput = "0.01";
        runTest("SimpleFlow", input, expectedOutput);
    }

    private static void testWhitespaceHandling() throws Exception {
        String input = "  B   20.0  \n  S \t 10.0 \n 5 \n Q ";
        String expectedOutput = "0.01";
        runTest("WhitespaceHandling", input, expectedOutput);
    }

    private static void runTest(String testName, String input, String expectedOutput) throws Exception {
        // Backup original System.in and System.out
        java.io.InputStream originalIn = System.in;
        java.io.PrintStream originalOut = System.out;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));

            Main.main(new String[0]);

            System.out.flush();
            String output = baos.toString(StandardCharsets.UTF_8.name());

            if (!output.equals(expectedOutput)) {
                throw new RuntimeException("Test " + testName + " failed. Expected: '" + expectedOutput + "', Got: '" + output + "'");
            }
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
