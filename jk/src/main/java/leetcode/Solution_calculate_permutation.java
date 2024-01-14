package leetcode;

import java.util.Arrays;

public class Solution_calculate_permutation {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2};
        permutation(arr, 0, arr.length - 1);
    }

    public static void permutation(int[] arr, int cursor, int end) {
        if (cursor == end) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = cursor; i <= end; i++) {
                if (!swapAccepted(arr, cursor, i)) {
                    continue;
                }
                swap(arr, cursor, i);
                permutation(arr, cursor + 1, end);
                swap(arr, cursor, i);
            }
        }
    }

    private static void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    private static boolean swapAccepted(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] == arr[end]) {
                return false;
            }
        }
        return true;
    }
}
