package leetcode

import codelab.kotlin.common.TreeNode
import codelab.kotlin.common.printPretty
import codelab.kotlin.common.tree4
import java.util.*

/**
 * 二叉树的右侧视图
 * [](https://leetcode-cn.com/problems/WNC0Lk/)
 */
class Solution_Code_Interviews_II_046 {
    fun rightSideView(root: TreeNode): List<Int> {
        val queue: Queue<TreeNode> = LinkedList()
        queue.offer(root)
        var result = mutableListOf<Int>()

        while (!queue.isEmpty()) {
            // 引入变量的必要性，for循环条件中queue.indices每次循环不会再变，但是循环内会变
            val indices = queue.indices
            for (i in indices) {
                val node = queue.poll()
                if (i == indices.last) result.add(node.data)
                node.left?.let { queue.offer(it) }
                node.right?.let { queue.offer(it) }
            }
        }

        return result
    }
}

fun main() {
    tree4.printPretty()
    println(
        Solution_Code_Interviews_II_046().rightSideView(tree4)
    )
}