package common

import kotlin.math.pow

private val arr1 = listOf(3, 9, 20, null, null, 15, 7, 5, 11).toTypedArray()
private val arr2 = listOf(1, 2, 3, null, 5, null, 4).toTypedArray()

@JvmField val tree1 = buildBinaryTree(arr1)
@JvmField val tree2 = buildBinaryTree(arr2)

fun maxDepth(root: TreeNode?): Int {
    return if (root == null) 0 else (1 + maxOf(maxDepth(root.left), maxDepth(root.right)))
}

fun TreeNode.show() {
    val depth = maxDepth(this)
    val h = depth * 2 - 1
    val w = (2f.pow(depth - 1) * 3 + 1).toInt()
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

private fun fillArray(root: TreeNode, row: Int, column: Int, arr: Array<Array<String>>, depth: Int) {
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