package test

import java.util.*

/**
 * 前缀树数据结构
 */
class Test28(val root: Node = Node()) {
    class Node(
        val isWord: Boolean = false,
        // var children: Array<Node?> = Array(26/* 大小取决于符合集合 */) { null }
        // 发现新大陆，arrayOfNulls方法，emptyArray()方法也由它实现
        var children: Array<Node?> = arrayOfNulls<Node?>(26)
        // 形参的类型即使在赋值了默认参数看似可以推断出来的情况下仍然要显式写出
        // var children = arrayOfNulls<Node?>(26) // WRONG
    )
}
fun main() {
    val arr = Array<IntRange>(3) { IntRange(0, it) }
    Arrays.sort(arr) { o1, o2 ->
        o1.last - o2.last
    }

    println(arr.contentToString())
    println(listOf(arr).toTypedArray().contentToString())
    println(listOf(*arr).toTypedArray().contentToString())
}