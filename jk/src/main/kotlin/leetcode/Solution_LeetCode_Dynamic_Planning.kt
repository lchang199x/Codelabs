package leetcode

import kotlin.math.max

class Solution_LeetCode_Dynamic_Planning {
    fun fibonacci(n: Int): Int {
        return if (n <= 1) 1 else fibonacci(n - 1) + fibonacci(n - 2)
    }

    fun fibonacciDp(n: Int): Int {
        if (n <= 1) return 1
        var pre1 = 1
        var pre2 = 1
        var result = 0

        for (i in 2 until n) {
            result = pre1 + pre2
            pre2 = pre1
            pre1 = result
        }

        return result
    }

    /**
     * C(N) = 2/N(0->N-1累加C(i)) + N
     */
    fun eval(n: Int): Double {
        var result = 0.0
        if (n == 0)
            result = 1.0
        else {
            var sum = 0.0
            for (i in 0 until n) {
                sum += eval(i)
            }
            result = 2.0 / n * sum + n
        }
        return result
    }

    /**
     * dp针对的是重复计算的部分，即0->N-1累加C(i)
     *
     * f(n)依赖0..n-1的子问题，所以要一个数组，fibonacci只依赖n-1和n-2，多以两个变量即可
     */
    fun evalDp(n: Int): Double {
        val nums = DoubleArray(n + 1)
        nums[0] = 1.0

        // 这里循环的人物不再是计算出result，而是计算出表nums中得每个item的值
        for (i in 1..n) {
            var sum = 0.0
            for (j in 0 until i)
                sum += nums[j]
            nums[i] = 2.0 * sum / i + i
        }
        return nums[n]
    }

    /**
     * 爬楼梯: 其实就是fibonacci数列
     * [](https://leetcode-cn.com/problems/climbing-stairs/)
     */
    fun climbStairs(n: Int): Int {
        var pre1 = 1
        var pre2 = 0
        var result  = 0

        for (i in 0 until n) {
            result = pre1 + pre2
            pre2 = pre1
            pre1 = result
        }

        return result
    }

    /**
     * 跳跃游戏
     * [](https://leetcode-cn.com/problems/jump-game/)
     */
    fun canJump(nums: IntArray): Boolean {
        var rightMost = 0
        for (i in nums.indices) {
            if (i < rightMost) {
                rightMost = max(rightMost, i + nums[i])
                if (rightMost > nums.size -1) {
                    return true
                }
            }
        }

        return false
    }
}

fun main() {
    println(Solution_LeetCode_Dynamic_Planning().evalDp(5))
}