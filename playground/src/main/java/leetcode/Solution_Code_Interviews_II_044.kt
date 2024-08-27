package leetcode

import codelab.kotlin.common.TreeNode
import codelab.kotlin.common.printPretty
import codelab.kotlin.common.tree1
import java.util.*

/**
 * 二叉树每层的最大值
 * [](https://leetcode-cn.com/problems/hPov7L/)
 */
class Solution_Code_Interviews_II_044 {
    fun largestValues(root: TreeNode?): List<Int> {
        if (root == null) return emptyList()

        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(root)
        val result = mutableListOf<Int>()

        while (!queue.isEmpty()) {
            // 还在考虑用0? 或者-1? 不如用Int.MIN_VALUE
            var max = Int.MIN_VALUE
            for (i in queue.indices) {
                val node = queue.poll()
                max = maxOf(max, node.data)
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
            result.add(max)
        }

        return result
    }
}

fun main() {
    tree1.printPretty()
    println(
        Solution_Code_Interviews_II_044().largestValues(tree1)
    )
}