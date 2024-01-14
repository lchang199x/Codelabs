package leetcode

/**
 * 最大子序列和
 * [](https://leetcode-cn.com/problems/maximum-subarray/)
 */
class Solution_LeetCode_53_Maximum_Sub_Array {
    fun maxSubArray(nums: IntArray): Int {
        // 初值是0， Int.MIN_VALUE or nums[0]?
        var result = nums[0]
        var temp = 0

        nums.forEach {
            temp += it

            if (it > temp) {
                temp = it
            }

            if (temp > result) {
                result = temp
            }
        }

        return result
    }
}

fun main() {
    println(Solution_LeetCode_53_Maximum_Sub_Array().maxSubArray(intArrayOf(-2, 1)))
}