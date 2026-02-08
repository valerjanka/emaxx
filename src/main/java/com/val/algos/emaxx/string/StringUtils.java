package com.val.algos.emaxx.string;

public class StringUtils {

    /**
     * Returns array of length string.length where i-th element equals to the
     * max count of symbols of sub-string that start from i and equals to prefix
     * of this string. The 0-th element = 0.
     * Description: <a href="http://e-maxx.ru/algo/z_function">http://e-maxx.ru/algo/z_function</a>
     * <p>
     * Examples: "abacaba" -> 0010301 "aaaaa" -> 04321
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param string array of chars
     * @return z-function
     */
    public static int[] zFunction(char[] string) {
        if (string == null)
            return null;
        int n = string.length;
        int[] result = new int[n];
        int left = 0;
        int right = 0;
        for (int i = 1; i < string.length; i++) {
            if (right > i) {
                result[i] = Math.min(result[i - left], right - i + 1);
            }
            while (i + result[i] < n && string[i + result[i]] == string[result[i]]) {
                ++result[i];
            }
            if (i + result[i] >= right) {
                left = i;
                right = i + result[i] - 1;
            }
        }
        return result;
    }

    /**
     * Calculates the prefix function for the given string.
     * <p>
     * <a href="http://e-maxx.ru/algo/prefix_function">http://e-maxx.ru/algo/prefix_function</a>
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param string the input string
     * @return an array representing the prefix function values
     */
    public static int[] prefixFunction(char[] string) {
        if (string == null)
            return null;
        int n = string.length;
        int[] p = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && string[i] != string[j]) {
                j = p[j - 1];
            }
            if (string[i] == string[j]) {
                ++j;
            }
            p[i] = j;
        }
        return p;
    }
}
