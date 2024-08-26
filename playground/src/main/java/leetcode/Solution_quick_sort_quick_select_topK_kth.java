package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/sort-an-array/
 * <p>
 * 快速排序是一种分治的递归算法
 * <p>
 * 步骤：选取pivot -> partition
 */
public class Solution_quick_sort_quick_select_topK_kth {

    /** 驱动程序 */
    public int[] quickSort(int[] nums) {
        randomizedQuickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // kth
    public int quickSelect(int[] nums, int k) {
        return randomizedQuickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    // 无需 if (l < r)，因为递归在 l>=r 之前一定会结束
    private int randomizedQuickSelect(int[] nums, int l, int r, int k) {
        int pos = randomizedPartition(nums, l, r);
        if (k == pos) {
            return nums[pos];
        } else if (k <= pos) {
            return randomizedQuickSelect(nums, l, pos - 1, k);
        } else {
            return randomizedQuickSelect(nums, pos + 1, r, k);
        }
    }

    /** 主例程 */
    public void randomizedQuickSort(int[] nums, int l, int r) {
        if (l < r) {
            int pos = randomizedPartition(nums, l, r);
            randomizedQuickSort(nums, l, pos - 1);
            randomizedQuickSort(nums, pos + 1, r);
        }
    }

    public int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l; // 随机选一个主元位置
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    public int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) { // 只有移动j，i被动增长
            if (nums[j] <= pivot) { // =pivot时j停止, 并交换元素
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {11, 15, 13, 12, 14};
        System.out.println(Arrays.toString(new Solution_quick_sort_quick_select_topK_kth().quickSort(nums)));
        System.out.println(new Solution_quick_sort_quick_select_topK_kth().quickSelect(nums, 2));
    }
}
