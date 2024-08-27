package ktest

import java.util.*

fun main() {
    val queue = PriorityQueue<String>().apply {
        offer("a")
        offer("1")
        offer("abc")
        offer("av")
        offer("ab")
        offer("bu")
        offer("cpu")
    }

    // forEach不保留插入序，也不保留字典序
    queue.forEach {
        println(it)
    }

    // 字典序正牌遍历方式
    while (!queue.isEmpty()) {
        println(queue.poll())
    }
}