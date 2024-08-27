package codelab.kotlin.common

fun swap(nums: IntArray, i: Int, j: Int) {
    if (i != j) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}