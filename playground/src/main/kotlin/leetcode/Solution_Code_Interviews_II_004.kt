package leetcode

/**
 * 只出现一次的数字
 * [](https://leetcode-cn.com/problems/WGki4K/)
 */
class Solution_Code_Interviews_II_004 {
    fun singleNumber(nums: IntArray): Int {
        // 什么时候用intArrayOf，什么时候用IntArray构造函数，什么时候用emptyArray<Int>
        val temp = IntArray(32)
        nums.forEach {
            for (i in 0..31) {
                temp[i] += (it shr (31 - i)) and  1
            }
        }
        var result = 0
        for (i in 0..31) {
            result = (result shl 1) + (temp[i] % 2)
        }

        return result
    }
}

fun main() {
    println(
        Solution_Code_Interviews_II_004().singleNumber(intArrayOf(1, 2, 3, 5, 1, 3, 2))
    )
}