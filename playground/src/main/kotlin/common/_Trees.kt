package common

import kotlin.math.pow

private val arr1 = listOf(3, 9, 20, null, null, 15, 7, 5, 11).toTypedArray()
private val arr2 = listOf(1, null, 2, 3).toTypedArray()
private val arr3 = listOf(2, null, 3, null, 4, null, 5, null, 6).toTypedArray()
private val arr4 = listOf(1, 2, 3, null, 5, null, 4).toTypedArray()

/**
 * 这里的tree都是从层序遍历中进行反序列化的
 *
 * 从前序遍历中反序列化tree1的方法为：
 * buildBinaryTree2(
 *       listOf(3, 9, null, null, 20, 15, null, null, 7, null, null).toTypedArray()
 * ).printPretty()
 */
@JvmField val tree1 = buildBinaryTree(arr1)
@JvmField val tree2 = buildBinaryTree(arr2)
@JvmField val tree3 = buildBinaryTree(arr3)
@JvmField val tree4 = buildBinaryTree(arr4)

fun KTreeNode.show() {
    val depth = maxDepth(this)
    // 行数，二叉树元素占一行，/ \这些连接符占一行，总共2 * depth -1行
    val h = depth * 2 - 1
    // 列数，以最后一行的最大宽度为准，最后一行有2^(depth-1)个元素
    val w = (2f.pow(depth - 1) * 3 + 1).toInt()
    // 填充空格比较关键
    val arr = Array(h) { Array(w) { " " } }

    fillArray(this, 0, w / 2, arr, depth)

    for (line in arr) {
        val sb = StringBuilder()
        var skip = 0
        for (i in line.indices) {
            if (i < skip) return
            sb.append(line[i])
            if (line[i].length > 1 && i <= line.size - 1) {
                skip = i + if (line[i].length > 4) 2 else line[i].length - 1
            }
        }
        println(sb.toString())
    }
}

private fun fillArray(root: KTreeNode, row: Int, column: Int, arr: Array<Array<String>>, depth: Int) {
    arr[row][column] = root.data.toString()
    val level = (row + 1) / 2
    if (level == depth) return

    val gap = depth - level - 1
    root.left?.let {
        arr[row + 1][column - gap] = "/"
        fillArray(it, row + 2, column - gap * 2, arr, depth)
    }
    root.right?.let {
        arr[row + 1][column + gap] = "\\"
        fillArray(it, row + 2, column + gap * 2, arr, depth)
    }
}