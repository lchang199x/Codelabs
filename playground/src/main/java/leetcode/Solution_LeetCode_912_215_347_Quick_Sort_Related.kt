package leetcode

import codelab.kotlin.common.swap
import java.util.*

/**
 * 快速排序相关的一些题目解法
 */
class Solution_LeetCode_912_215_347_Quick_Sort_Related {
    /**
     * 排序数组: LeetCode_912
     * [](https://leetcode-cn.com/problems/sort-an-array/)
     */
    fun sortArray(nums: IntArray): IntArray {
        quickSort(nums, 0, nums.size - 1)
        return nums
    }

    /**
     * 数组中第k大元素: LeetCode_215
     * [](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)
     *
     * 另堆排序解法
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        return quickSelect(nums, 0, nums.size - 1, nums.size - k)
    }

    /**
     * 前k个高频元素：LeetCode_347
     * [](https://leetcode-cn.com/problems/top-k-frequent-elements/)
     */
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }

        val list = mutableListOf<IntArray>()
        map.forEach {
            list.add(intArrayOf(it.key, it.value))
        }

        // list.size - k 大法好
        return quickTopK(list, 0, list.size - 1, list.size - k)
    }

    private fun quickTopK(list: List<IntArray>, l: Int, r: Int, k: Int): IntArray {
        val index = (l..r).random()
        Collections.swap(list, index, r)
        // 当randomizedPartition和partition写在一起时，记住时nums[r]，不是nums[index]
        val pivot = list[r][1]

        var i = l - 1
        // 当list.size == 1时这个for循环不会执行，导致i的值只会在后面自增一次，即k=1，但i=0
        // 注意相同的情况kthLargest将k=1转化为了k=0
        for (j in l until r) {
            if (list[j][1] <= pivot) {
                Collections.swap(list, ++i, j)
            }
        }
        Collections.swap(list, ++i, r)

        return if (k == i) {
            list.subList(k, list.size).map { it[0] }.toIntArray()
        } else if (k < i) {
            quickTopK(list, l, i - 1, k)
        } else {
            quickTopK(list, i + 1, r, k)
        }
    }

    private fun quickSort(nums: IntArray, l: Int, r: Int) {
        if (l < r) {
            val pos = randomizedPartition(nums, l, r)
            quickSort(nums, l, pos - 1)
            quickSort(nums, pos + 1, r)
        }
    }

    private fun quickSelect(nums: IntArray, l: Int, r: Int, k: Int): Int {
        val pos = randomizedPartition(nums, l, r)
        return if (k == pos) {
            nums[k]
        } else if (k < pos) {
            quickSelect(nums, l, pos - 1, k)
        } else {
            quickSelect(nums, pos + 1, r, k)
        }
    }

    private fun randomizedPartition(nums: IntArray, l: Int, r: Int): Int {
        val i = (l..r).random()
        swap(nums, r, i)
        return partition(nums, l, r)
    }

    private fun partition(nums: IntArray, l: Int, r: Int): Int {
        val pivot = nums[r]
        var i = l - 1
        for (j in l until r) {
            if (nums[j] <= pivot) {
                i += 1
                swap(nums, i, j)
            }
        }
        swap(nums, i + 1, r)
        return i + 1
    }
}

fun main() {
    println(
        Solution_LeetCode_912_215_347_Quick_Sort_Related()
            .sortArray(intArrayOf(1, 5, 3, 2, 4)).contentToString()
    )
    println(
        Solution_LeetCode_912_215_347_Quick_Sort_Related()
            .findKthLargest(intArrayOf(1, 5, 3, 2, 4), 2)
    )
    println(
        Solution_LeetCode_912_215_347_Quick_Sort_Related()
            .topKFrequent(intArrayOf(1), 1).contentToString()
    )
    println(
        Solution_LeetCode_912_215_347_Quick_Sort_Related()
            .topKFrequent(intArrayOf(1, 1, 5, 3, 3, 3, 2, 5, 4), 2).contentToString()
    )
}