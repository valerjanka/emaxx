package com.val.algos.other.sort;

import java.util.Arrays;

public class IntRadixSort {

    public static void main(String[] arr) {
        System.out.println(Arrays.toString(new IntRadixSort().sort(new int[]{10, 100})));
        System.out.println(Arrays.toString(new IntRadixSort().sort(new int[]{3, 2, 1})));
        System.out.println(Arrays.toString(new IntRadixSort().sort(new int[]{3312, 2323, 1213231, 2352, -1231231322, -1, -2, 0})));
    }

    public int[] sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int max = Arrays.stream(arr).map(Math::abs).max().getAsInt();
        int[] count = new int[20];
        int[] fromPosition = new int[20]; // support negative
        int[] curPosition = new int[20]; // support negative

        for (int digit = 1; digit <= max; digit *= 10) {
            countSort(arr, digit, count, fromPosition, curPosition);
        }
        return arr;
    }

    private void countSort(int[] arr, int exp, int[] count, int[] fromPosition, int[] curPosition) {
        Arrays.fill(count, 0);
        for (int el : arr) {
            count[(el / exp) % 10 + 10]++;
        }
        // [0, 2,0, 4] -> 0, 2, 2, 6
        fromPosition[0] = 0;
        curPosition[0] = fromPosition[0];
        for (int i = 1; i < 20; i++) {
            fromPosition[i] = fromPosition[i - 1] + count[i - 1];
            curPosition[i] = fromPosition[i];
        }
        for (int i = 0; i < arr.length; i++) {
            int el = arr[i];
            int digit = (el / exp) % 10 + 10;
            if (curPosition[digit] == i) {
                ++curPosition[digit];
            } else {
                while (i < fromPosition[digit] || i >= curPosition[digit]) {
                    swap(i, curPosition[digit], arr);
                    curPosition[digit]++;
                    el = arr[i];
                    digit = (el / exp) % 10 + 10;
                }
            }
        }
    }

    private void swap(int i, int o, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[o];
        arr[o] = tmp;
    }
}
