package test

import common.swap

class Test23 {
    /**
     * 注意内层循环每次都是从0开始，结束位置和i有关
     */
    fun bubbleSort(nums: IntArray) {
        if (nums.isEmpty() || nums.size == 1) {
            return
        }

        nums.forEachIndexed { i, v ->
            for (j in 0 until nums.size - i - 1) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1)
                }
            }
        }
    }
}

fun main() {
    val arr = intArrayOf(1, 5, 3, 9, 7)
    Test23().bubbleSort(arr)
    println(arr.contentToString())
}