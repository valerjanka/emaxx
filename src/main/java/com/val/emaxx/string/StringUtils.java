package com.val.emaxx.string;

public class StringUtils {

    /**
     * Returns array of length string.length where i-th element equals to the
     * max count of symbols of sub-string that start from i and equals to prefix
     * of this string. The 0-th element = 0.
     * Description: http://e-maxx.ru/algo/z_function
     * 
     * Examples: "abacaba" -> 0010301 "aaaaa" -> 04321
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

    public static int[] prefixFunction(char[] string) {
        if (string == null)
            return null;
        int n = string.length;
        int[] p = new int[n];
        int j = 0;
        for(int i = 1; i < n; i++) {
            while(j>0 && string[i] != string[j]) {
                j= p[j-1];
            }
            if(string[i] == string[j]) {
                ++j;
            }
            p[i] = j;
        }
        return p;
    }
}
