package com.val.algos.other.sort;

import java.util.Arrays;

public class IntRadixSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new IntRadixSort().sort(new int[]{10, 100})));
        System.out.println(Arrays.toString(new IntRadixSort().sort(new int[]{3, 2, 1})));
        System.out.println(Arrays.toString(new IntRadixSort().sort(new int[]{3312, 2323, 1213231, 2352, -1231231322, -1, -2, 0})));
    }

    public int[] sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        // Transform to treat as unsigned (preserving order for signed int)
        // by toggling the sign bit: 0x80000000 maps to 0, 0 maps to 0x80000000, etc.
        for (int i = 0; i < arr.length; i++) {
            arr[i] ^= 0x80000000;
        }

        int[] buffer = new int[arr.length];
        // 4 passes, 8 bits each
        for (int shift = 0; shift < 32; shift += 8) {
            countingSort(arr, buffer, shift);
            // Swap references to avoid copying back every time
            int[] temp = arr;
            arr = buffer;
            buffer = temp;
        }

        // After 4 swaps, arr is the original reference if we started with arr -> buffer -> arr -> buffer -> arr.
        // Wait:
        // Pass 0: arr -> buffer. arr becomes buffer.
        // Pass 1: buffer -> arr. arr becomes original arr.
        // Pass 2: arr -> buffer. arr becomes buffer.
        // Pass 3: buffer -> arr. arr becomes original arr.
        // So 'arr' variable points to the array with sorted data.

        // Restore sign bit
        for (int i = 0; i < arr.length; i++) {
            arr[i] ^= 0x80000000;
        }

        return arr;
    }

    private void countingSort(int[] input, int[] output, int shift) {
        int[] count = new int[256];
        int mask = 0xFF;

        // Count frequencies
        for (int el : input) {
            count[(el >> shift) & mask]++;
        }

        // Compute cumulative counts (starting positions)
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Place elements into output array (iterate backwards for stability)
        for (int i = input.length - 1; i >= 0; i--) {
            int el = input[i];
            int bucket = (el >> shift) & mask;
            output[--count[bucket]] = el;
        }
    }
}
