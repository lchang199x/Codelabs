@file:JvmName("Trees")

package codelab.kotlin.common

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class TreeNode(val data: Int, var left: TreeNode? = null, var right: TreeNode? = null)

class TrieNode(var isWord: Boolean = false, val children: Array<TrieNode?> = arrayOfNulls(26))

/**
 * 二叉树的最大深度
 * [](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
 */
fun maxDepth(root: TreeNode?): Int {
    return if (root == null) 0 else (1 + maxOf(maxDepth(root.left), maxDepth(root.right)))
}

/**
 * 二叉树的最小深度
 * [](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)
 * 注意 left 为null时，最小深度不是 1 + 0，而是 1 + minDepth(right)
 */
fun minDepth(root: TreeNode?): Int {
    if (root == null) return 0

    return if (root.left == null) minDepth(root.right) + 1
    else if (root.right == null) minDepth(root.left) + 1
    else 1 + minOf(minDepth(root.left), minDepth(root.right))
}

fun TreeNode?.printPretty() {
    if (this == null) return println("NULL_TREE!")
    show()
}

/**
 * 二叉树反序列化：Recursion
 *
 * 输入为前序遍历的结果 [3,9,null,null,20,15,null,null,7,null,null]
 *
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 */
fun buildBinaryTree2(arr: Array<Int?>?): TreeNode {
    if (arr.isNullOrEmpty()) return TreeNode(-1)
    val i = intArrayOf(0)
    return deserialize(arr, i)!!
}

private fun deserialize(arr: Array<Int?>, i: IntArray): TreeNode? {
    // i[0]要每次迭代都更新，即使return null
    // 只有用带个元素的数组记录下标，这里return掉之后还能传递给下一轮迭代中的i
    val value = arr[i[0]++] ?: return null

    val node = TreeNode(value)
    node.left = deserialize(arr, i)
    node.right = deserialize(arr, i)
    return node
}

/**
 * 二叉树反序列化
 *
 * 输入为层序遍历的结果 [3,9,20,null,null,15,7]
 *
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 */
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

/**
 * 二叉树的前序遍历: Recursion
 * [](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
 */
fun preorderTraversal(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    preorder(root, result)
    return result
}

/**
 * 二叉树的前序遍历：Iteration
 * [](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
 */
fun preorderTraversal2(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    var cur = root
    val stack = Stack<TreeNode>()
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) {
            result.add(cur.data)
            stack.push(cur)
            cur = cur.left
        }

        cur = stack.pop()
        cur = cur.right
    }

    return result
}

private fun preorder(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) return

    list.add(root.data)
    preorder(root.left, list)
    preorder(root.right, list)
}

/**
 * 二叉树的中序遍历: Recursion
 * [](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
 */
fun inorderTraversal(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    inorder(root, result)
    return result
}

/**
 * 二叉树的中序遍历: Iteration
 * [](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
 */
fun inorderTraversal2(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    var cur = root
    val stack = Stack<TreeNode>()

    // cur为空时表示上一轮cur.right为空，stack里面还是有的
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }

        cur = stack.pop()
        // 每轮循环只遍历这一个节点
        result.add(cur.data)
        cur = cur.right
    }

    return result
}

private fun inorder(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) return

    inorder(root.left, list)
    list.add(root.data)
    inorder(root.right, list)
}

/**
 * 二叉树的后序遍历: Recursion
 * [](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
 */
fun postorderTraversal(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    postorder(root, result)
    return result
}

/**
 * 二叉树的后序遍历: Iteration
 * [](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
 */
fun postorderTraversal2(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val result = mutableListOf<Int>()
    var cur = root
    // var pre = null as TreeNode?
    var pre: TreeNode? = null
    val stack = Stack<TreeNode>()
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }

        // peek vs pop
        cur = stack.peek()
        // 到达一个节点时先检查它的右子树有没有遍历过
        if (cur.right != null && cur.right != pre) {
            cur = cur.right
        } else {
            stack.pop()
            result.add(cur.data)
            pre = cur
            cur = null
        }
    }

    return result
}

private fun postorder(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) return

    inorder(root.left, list)
    inorder(root.right, list)
    list.add(root.data)
}

/**
 * 二叉树的层序遍历，广度优先遍历 bfs: breadth first search
 * 用队列，按节点输出
 */
fun levelOrderTraversal2(root: TreeNode?): List<Int> {
    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root ?: return emptyList())

    val result = mutableListOf<Int>()
    while (!queue.isEmpty()) {
        val temp = queue.poll()
        result.add(temp.data)

        temp.left?.let { queue.offer(it) }
        temp.right?.let { queue.offer(it) }
    }

    return result
}

/**
 * 二叉树的层序遍历，广度优先遍历 bfs: breadth first search
 * [](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
 * 用队列，按层输出
 */
fun levelOrderTraversal(root: TreeNode?): List<List<Int>> {
    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root ?: return emptyList())

    val result = mutableListOf<List<Int>>()
    while (!queue.isEmpty()) {
        // 想用clear？ 每次add到result时ArrayList(list)包装一下就行了
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

/**
 * 二叉树锯齿形层序遍历
 * [](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)
 * 控制list顺序，而不是控制queue顺序
 */
fun zigzagLevelOrderTraversal(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()

    val queue: Queue<TreeNode> = LinkedList()
    queue.offer(root)
    val result = mutableListOf<List<Int>>()
    var leftToRight = true
    while (!queue.isEmpty()) {
        val list = LinkedList<Int>()
        for (i in queue.indices) {
            val node = queue.poll()
            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }

            if (leftToRight) {
                list.offerLast(node.data)
            } else {
                list.offerFirst(node.data)
            }
        }
        result.add(list)
        leftToRight = !leftToRight
    }

    return result
}

@JvmField
val NULL_TREE: TreeNode? = null

fun main() {
    NULL_TREE.printPretty()
    tree1.printPretty()
    tree2.printPretty()
    tree3.printPretty()

    println("\nlevel order:")
    println(levelOrderTraversal(tree1))
    println(levelOrderTraversal(tree2))
    println(levelOrderTraversal(tree3))

    println("\nzigzag level order:")
    println(zigzagLevelOrderTraversal(tree1))
    println(zigzagLevelOrderTraversal(tree2))
    println(zigzagLevelOrderTraversal(tree3))

    println("\npre order:")
    println(preorderTraversal(tree1))
    println(preorderTraversal(tree2))
    println(preorderTraversal(tree3))

    println("\nin order:")
    println(inorderTraversal(tree1))
    println(inorderTraversal(tree2))
    println(inorderTraversal(tree3))

    println("\npost order:")
//    println(postorderTraversal(NULL_TREE))
    println(postorderTraversal(tree1))
    println(postorderTraversal(tree2))
    println(postorderTraversal(tree3))

    println("\nmax depth:")
    println(maxDepth(tree1))
    println(maxDepth(tree2))
    println(maxDepth(tree3))

    println("\nmin depth:")
    println(minDepth(tree1))
    println(minDepth(tree2))
    println(minDepth(tree3))
}