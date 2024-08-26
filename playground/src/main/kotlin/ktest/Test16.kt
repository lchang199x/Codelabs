package ktest

fun main() {
    println('1' in '0'..'9')
    println('1'.isDigit())
    println(Character.isDigit('1'))
    println(Character.isLetterOrDigit('1'))
    println(Character.isWhitespace('1'))
    println(Character.isLowerCase('1'))
    println(Character.isLowerCase('c'))
    // 可以while循环判断一个string是不是数字，每一个char都是数字则是数字
    println('A'.isDigit())
    println('A'.isLetter())
}