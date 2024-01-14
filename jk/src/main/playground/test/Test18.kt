package test

fun main() {
    // 溢出
    val n: Int = 255 shl 24
    println(n)
    // n为负数时，结果为2^32 - n
    println(Integer.toBinaryString(n))

    println("11".parseInt())

    // java中的concat可用做同样的事，while (s.length < 8) s = s.concat("0"); while (s.length < 8) s = '2'.concat(s)
    println("11".padEnd(5, '0').padStart(8, '2'))

}