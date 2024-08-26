package leetcode

import kotlin.math.min

/**
 * 和大于等于 target 的最短子数组
 * [](https://leetcode-cn.com/problems/2VG8Kg/)
 */
class Solution_Code_Interviews_II_008 {
    fun minSubArrayLen(nums: IntArray, target: Int): Int {
        if (nums.isEmpty()) return 0

        var l = 0
        var r = 0

        // 因为后面是计算min值，所以这里尽量大，让位真实值
        var result = Int.MAX_VALUE
        var temp = 0
        while (r < nums.size) {
            temp += nums[r]

            while (l <= r && temp >= target) {
                result = min(result, r - l + 1)
                temp -= nums[l++]
            }
            r++
        }
        return if (result == Int.MAX_VALUE) 0 else result
    }
}

fun main() {
    println(Solution_Code_Interviews_II_008().minSubArrayLen(intArrayOf(2, 3, 1, 2, 4, 3), 7))
}