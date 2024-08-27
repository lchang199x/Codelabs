package ktest

import java.util.*

fun main() {
    println('1'.toInt()) // 49
    println('1'.code) // 49
    println('1' - '0') // 1
    println('1'.toString().toInt()) // 1

    // ascii码表出现的顺序是数字 大写字母  小写字母
    println('a' - 'A')  // 32
    println('A' - 'a')  // -32
    println('A' - '0')  // 17

    // 输入带空格dfdfs dfdfd
    println(Scanner(System.`in`).next()) // dfdfs
    println(Scanner(System.`in`).nextLine()) // dfdfs dfdfd
}