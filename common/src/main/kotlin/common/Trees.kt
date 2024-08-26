@file:JvmName("Trees")

package common

import java.util.*

class TreeNode(val data: Int, var left: TreeNode? = null, var right: TreeNode? = null)

fun TreeNode?.printPretty() {
    if (this == null) return println("NULL_TREE!")
    show()
}

fun buildBinaryTree(arr: Array<Int?>?): TreeNode {
    if (arr.isNullOrEmpty()) return TreeNode(-1)
    val root = TreeNode(arr[0]!!)

    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root)
    var count = 1
    while (!queue.isEmpty()) {
        val node = queue.poll()
        if (count < arr.size) {
            arr[count++]?.let {
                node.left = TreeNode(it)
                queue.offer(node.left)
            }
        }
        if (count < arr.size) {
            arr[count++]?.let {
                node.right = TreeNode(it)
                queue.offer(node.right)
            }
        }
    }
    return root
}

@JvmField
val NULL_TREE: TreeNode? = null

fun main() {
    NULL_TREE.printPretty()
    tree1.printPretty()
    tree2.printPretty()
}