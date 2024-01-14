package test

import common.KListNode

fun main() {
    val list = mutableListOf<KListNode?>()
    var node1: KListNode? = KListNode(1, null)
    val node2: KListNode? = KListNode(2, null)
    list.add(node1)
    list.add(node2)
    node1 = null
    println(list)
    node2?.next = KListNode(2)
    println(list)
}