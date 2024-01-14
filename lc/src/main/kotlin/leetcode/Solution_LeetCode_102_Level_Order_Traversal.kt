package leetcode

import common.*
import java.util.*

/**
 * Level Order Traversal
 * [](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
 */
class Solution_LeetCode_102_Level_Order_Traversal {
    fun levelOrderTraversal2(root: TreeNode?): List<List<Int>> {
        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(root ?: return emptyList())

        val result = mutableListOf<List<Int>>()
        while (!queue.isEmpty()) {
            val list = mutableListOf<Int>()
            for (i in queue.indices) {
                val node = queue.poll()
                list.add(node.data)
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
            result.add(list)
        }

        return result
    }
}

fun main() {
    NULL_TREE.printPretty()
    tree1.printPretty()
    tree2.printPretty()

    println(Solution_LeetCode_102_Level_Order_Traversal().levelOrderTraversal2(NULL_TREE))
    println(Solution_LeetCode_102_Level_Order_Traversal().levelOrderTraversal2(tree1))
    println(Solution_LeetCode_102_Level_Order_Traversal().levelOrderTraversal2(tree2))
}