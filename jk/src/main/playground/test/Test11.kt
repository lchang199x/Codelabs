package test

fun main() {
    // 等价于 IntArray(8) { 0 }，会创建一个长度为8的数组并初始化为全0
    IntArray(8).forEach {
        println(it)
    }
    IntArray(8) { 1 } .forEach {
        println(it)
    }
}