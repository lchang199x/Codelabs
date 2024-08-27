package leetcode

/**
 * 和为k的子数组
 * [](https://leetcode-cn.com/problems/QTMn0o/)
 */
class Solution_Code_Interviews_II_010 {
    fun minSubArray(nums: IntArray, k: Int): Int {
        if (nums.isEmpty()) return 0

        var result = 0
        var temp = 0
        val map = hashMapOf<Int, Int>().apply {
            this[0] = 1 // 初始边界条件不能少，第一个被找到的 temp-k=0，需要有一个0在map里
        }
        for (i in nums.indices) {
            temp += nums[i]
            // 这里先result在map.put，否则result加的1可能是put进去的
            result += map.getOrDefault(temp - k, 0)
            map[temp] = map.getOrDefault(temp, 0) + 1
        }
        return result
    }
}

fun main() {
    println(Solution_Code_Interviews_II_010().minSubArray(intArrayOf(1, 1, 1), 2))
}