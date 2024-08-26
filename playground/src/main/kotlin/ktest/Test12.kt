package ktest

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        printN(scanner.next())
    }
}

private fun printN(ss: String) {
    var s = ss
    while (true) {
/*        while (s.length < 8)
            s += "0"*/
        s = s.padEnd(8, '0')
        println(s.substring(0, 8))

        if (s.length == 8) return
        s = s.substring(8, s.length)
    }
}