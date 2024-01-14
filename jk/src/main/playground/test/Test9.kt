package test

import java.util.*

fun main() {
    println(readLine()/*?.charAt() kotlin中的String不是java中的String没有charAt方法，可以用下标访问s[0]*/)

    val scanner = Scanner(System.`in`)
    println(scanner.nextInt())
    println(scanner.next())
}