package test

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        val count = scanner.nextInt()
        val temp = mutableSetOf<Int>()
        for (i in 0 until count) {
            temp.add(scanner.nextInt())
        }
        val arr = temp.toIntArray()
        Arrays.sort(arr)
        arr.forEach { println(it) }
    }
}