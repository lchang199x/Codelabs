package leetcode;

import java.util.*;

/**
 * 数学问题：数组交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/ 无重复
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/ 有重复
 */
public class Solution_Intersection_of_two_arrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(join1(new int[]{1, 1, 3, 2, 5}, new int[]{2, 1, 1, 4, 7})));
        System.out.println(Arrays.toString(join2(new int[]{1, 1, 3, 2, 5}, new int[]{2, 1, 1, 4, 7})));
    }

    public static Integer[] join1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return null;
        }
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }

        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        Integer[] intersection = new Integer[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }

    public static Integer[] join2(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : arr2) {
            if (map.containsKey(i)) {
                result.add(i);
                int count = map.get(i) - 1;
                if (count == 0) {
                    map.remove(i);
                } else {
                    map.put(i, map.get(i) - 1);
                }
            }
        }
        return result.toArray(new Integer[0]);
    }
}
