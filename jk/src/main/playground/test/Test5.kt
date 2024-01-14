package test

import common.swap

class Test5 {
    fun sortArray(nums: IntArray): IntArray {
        quickSort(nums, 0, nums.size - 1)
        return nums
    }

    private fun quickSort(nums: IntArray, l: Int, r: Int) {
        if (l < r) {
            val pos = randomizedPartition(nums, l, r)
            quickSort(nums, l, pos - 1)
            quickSort(nums, pos + 1, r)
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
            // 快速排序降序真的就这么简单
            if (nums[j] >= pivot) {
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
        Test5()
            .sortArray(intArrayOf(1, 5, 3, 2, 4)).contentToString()
    )
}