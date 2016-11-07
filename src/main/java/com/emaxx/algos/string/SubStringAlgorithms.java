package com.emaxx.algos.string;


public class SubStringAlgorithms {
    private static final char SEPARATOR = '#';

    public static int leastCompression(char[] string) {
        int[] zFunction = StringUtils.zFunction(string);
        int n = string.length;
        for(int i = 0; i < n; i++) {
            if(i + zFunction[i] == n && n % i == 0) {
                return i;
            }
        }
        return 0;
    }
    
    public static int substringAmount(char[] string, char[] substring) {
        if (string == null || string.length == 0 || substring == null || substring.length == 0) {
            return 0;
        }
        int substringLength = substring.length;
        char[] newString = new char[string.length + 1 + substringLength];
        copy(newString, substring, 0);
        newString[substringLength] = SEPARATOR;
        copy(newString, string, substringLength + 1);
        int[] zFunction = StringUtils.zFunction(newString);
        int result = 0;
        for (int curZFunction : zFunction) {
            if (curZFunction == substringLength)
                ++result;
        }
        return result;
    }

    private static void copy(char[] toString, char[] fromString, int toPosition) {
        if (toString.length < fromString.length + toPosition)
            throw new IllegalArgumentException("toString has no enouph length to copy");
        System.arraycopy(fromString, 0, toString, toPosition, fromString.length);

    }
}
