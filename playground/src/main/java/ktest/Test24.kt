package ktest

import codelab.kotlin.common.ListNode

fun main() {
    val node = ListNode(2, ListNode(2))
    test24_2(node)
    // 输出3，虽然局部变量和形参同名，但只要指向同一对象，那操作就是生效的
    println(node.next?.`val`)
}

fun test24(node: ListNode) {
    // Name shadowed: 即作用域内node都是指局部变量
    // 局部变量node将形参变为另一个不可变变量，一句废话
    val node = node
    node.next = ListNode(3)
    println(node.next?.`val`)
}

fun test24_1(node: ListNode) {
    // Name shadowed: 即作用域内node都是指局部变量
    node.next = ListNode(3)
    println(node.next?.`val`)
}

fun test24_2(node: ListNode) {
    // Name shadowed: 即作用域内node都是指局部变量
    // 局部变量node将形参变为可变变量
    var node = node
    node = ListNode(node.`val` + 1)
    println(node.`val`)
}