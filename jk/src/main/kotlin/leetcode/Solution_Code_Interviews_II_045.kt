package leetcode

import common.KTreeNode
import common.printPretty
import common.tree1
import java.util.*

/**
 * 二叉树最底层最左边的值
 * [](https://leetcode-cn.com/problems/LwUNpT/)
 */
class Solution_Code_Interviews_II_045 {
    fun bottomLeftValue(root: KTreeNode): Int {
        val queue: Queue<KTreeNode> = LinkedList()
        queue.offer(root)
        var result = root.data

        while (!queue.isEmpty()) {
            for (i in queue.indices) {
                val node = queue.poll()
                if (i == 0) result = node.data
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
        }

        return result
    }
}

fun main() {
    tree1.printPretty()
    println(
        Solution_Code_Interviews_II_045().bottomLeftValue(tree1)
    )
}