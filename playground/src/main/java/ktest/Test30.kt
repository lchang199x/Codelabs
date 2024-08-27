package ktest

/**
 * kotlin从标准输入读取二元数组并输出
 */
fun main() {
    val n = readLine()!!.trim().toInt()
    val arr = Array<Array<Int>>(n) { Array(n) { 0 } }

    for (i in arr.indices) {
        arr[i] = readLine()!!.trimStart().trimEnd().split(" ").map { it.toInt() }.toTypedArray()
    }

    println(arr.contentToString())
    println(arr.contentDeepToString()) // √
}