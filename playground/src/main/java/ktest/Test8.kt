package ktest

import codelab.kotlin.common.ListNode

fun main() {
    val list = mutableListOf<ListNode?>()
    var node1: ListNode? = ListNode(1, null)
    val node2: ListNode? = ListNode(2, null)
    list.add(node1)
    list.add(node2)
    node1 = null
    println(list)
    node2?.next = ListNode(2)
    println(list)
}