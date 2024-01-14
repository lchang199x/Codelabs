package test

fun main() {
    println(setOf(1, 2, 3).fold(0, Int::plus))
    /** String is subtype of String? */
    println("" is String)
    println("" is String?)
    println(null is String)
    println(null is String?)
}