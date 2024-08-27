package jtest;

import java.util.HashMap;
import java.util.Map;

public class Solution_find_repeat_number {
    public static void main(String[] args) {
        System.out.println(findRepeatedNumber(new int[] {1, 2, 2, 5, 4, 4, 4}));
    }

    private static Integer findRepeatedNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int max = -1;
        Integer value = null;
        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
            if (i.getValue() > max) {
                max = i.getValue();
                value = i.getKey();
            }
        }

        return value;

    }
}
