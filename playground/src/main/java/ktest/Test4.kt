package ktest

import java.util.*

fun main() {
    val arr = intArrayOf(5, 1, 3, 2, 4)
    println(arr.size)
    println(arr.count())
    println(arr.indices)
    println(arr.indices.last)
    println(0..arr.size)
    println(0 until arr.size step 2)
    println(arr.count() downTo 0 step 1)

    var i = 0
    println(arr[i++])
    println(i)

    val range = (1..5)
    for (index in range) {
        println(index)
    }

    // kotlin中swap函数的实现
    arr[0] = arr[1].apply {
        arr[1] = arr[0]
    }

    println("arr[0]: ${arr[0]}, arr[1]: ${arr[1]}")

    // PriorityQueue默认为最小堆
    println(PriorityQueue<Int>().apply {
        addAll(arr.asIterable())
    }.poll())

    val arr2D = arrayOf<IntArray>(intArrayOf(1, 2), intArrayOf(3, 4),  intArrayOf(5, 6))
    println(arr2D[0].contentToString())
    println(arr2D[1].contentToString())
    // subList(inclusive, exclusive)
    println(arr2D.asList().subList(1, 3).toTypedArray()[1].contentToString())
    println(arr2D.slice(1..2)[1].contentToString())

    for (i in 0 until 0) {
        println("until $i")
    }

    for (i in 0..0) {
        println("..$i")
    }

    // 算数运算符优先级高于..
    for (i in 1..3-1) {
        println(i)
    }

    for (i in 10 downTo 0) {
        print(i)
    }
}