package test

import java.util.*

fun main() {
    val s = Scanner(System.`in`)
    while (s.hasNext()) {
        var result = 0
        s.nextLine().substring(2).uppercase().forEach {
            result = if (it in setOf('A', 'B', 'C', 'D', 'E', 'F')) {
                result * 16 + 10 + (it - 'A')
            } else {
                result * 16 + (it - '0')
            }
        }

        println(result)
    }
}