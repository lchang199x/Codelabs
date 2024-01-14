package leetcode

import java.util.*

/**
 * 两数之和
 */
class Solution_LeetCode_1_Two_Sum_Three_Sum {
    /**
     * 无序数组：空间换时间
     * [](https://leetcode-cn.com/problems/two-sum/)
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        nums.forEachIndexed { index, i ->
            if (map.containsKey(target - nums[index])) {
                return intArrayOf(index, map[target - nums[index]]!!)
            }
            map[nums[index]] = index
        }
        return intArrayOf()
    }

    /**
     * 排序数组：双指针
     * [](https://leetcode-cn.com/problems/kLl5u1/)
     */
    fun twoSumForSortedArray(nums: IntArray, target: Int): IntArray {
        var l = 0
        var r = nums.size - 1

        while (l < r) {
            val sum = nums[l] + nums[r]
            if (sum == target) {
                return intArrayOf(l, r)
            } else if (sum < target) {
                l++
            } else {
                r--
            }
        }

        return intArrayOf()
    }

    /**
     * 数组中和为0的3个数字
     * [](https://leetcode-cn.com/problems/1fGaJU/)
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()
        val result = mutableListOf<List<Int>>()
        Arrays.sort(nums)
        // kotlin中for循环i为val不便操作，一般用while
        var i = 0
        while (i < nums.size - 2) {
            twoSumForThreeSum(nums, i, result)
            val temp = nums[i]
            // 排序之后这种连续跳过重复元素才成为可能
            while (i < nums.size && temp == nums[i]) {
                ++i
            }
        }
        return result
    }

    private fun twoSumForThreeSum(nums: IntArray, i: Int, result: MutableList<List<Int>>) {
        var j = i + 1
        var k = nums.size - 1

        while (j < k) {
            if (nums[i] + nums[j] + nums[k] == 0) {
                result.add(listOf(nums[i], nums[j], nums[k]))
                val temp = nums[j]
                while (j < k && nums[j] == temp) {
                    j++
                }
            } else if (nums[i] + nums[j] + nums[k] < 0) {
                j++
            } else {
                k--
            }
        }
    }
}

fun main() {
    println(Solution_LeetCode_1_Two_Sum_Three_Sum().threeSum(intArrayOf(-1, 0, 1, 2, -1, 4)))
    println(Solution_LeetCode_1_Two_Sum_Three_Sum().threeSum(intArrayOf(1, -1, -1, 0)))
}