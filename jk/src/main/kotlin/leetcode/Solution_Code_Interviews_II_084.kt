package leetcode

import common.swap

class Solution_Code_Interviews_II_084 {
    fun permutation(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        backTracking(nums, 0, result)
        return result
    }

    private fun backTracking(nums: IntArray, index: Int, result: MutableList<List<Int>>) {
        if (index == nums.size) {
            // 这里的permutation不需要在每一次递归中传递，所以不用作为参数传递进来
            val permutation = arrayListOf<Int>()
            nums.forEach { permutation.add(it) }
            result.add(permutation)
        } else {
            val set = mutableSetOf<Int>()
            for (i in index until nums.size) {
                if (!set.contains(nums[i])) {
                    set.add(nums[i])
                    // Collections中只有交换List中两个元素的swap方法，交换两个数组元素的方法是private的
                    swap(nums, index, i)
                    backTracking(nums, index + 1, result)
                    swap(nums, index, i)
                }
            }
        }
    }
}

fun main() {
    println(Solution_Code_Interviews_II_084().permutation(intArrayOf(1, 2, 2, 3)))
}