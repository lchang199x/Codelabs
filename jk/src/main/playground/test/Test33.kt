package test

fun main() {
    val i: Int = 0
    val j = intArrayOf(0)
    serialize(i, j)
    println("$i, ${j[0]}")
}

private fun serialize(i: Int, j: IntArray) {
    var i = i
    i++
    j[0]++
}