package test

import java.util.*

fun main() {
    val size = "com.tptz.ui.MainActivity.java".split("Activity.java").size
    val s1 = "com.tptz.ui.MainActivity.java".split("Activity.java")[0]
    val s2 = "com.tptz.ui.MainActivity.java".split("Activity.java")[1]
    val s3 = "com.tptz.ui.MainActivity.java".split("Activity.java")[0].split(".").last()
    println(size) // 2
    println(s1)
    println(s2 == "") // true
    println(s3) // Main
    println("c"[0])
    println("cc".toUpperCase())
    println("cc".uppercase())
    println("cc".capitalize())
    println("cc".capitalize(Locale.getDefault()))
    println("cc".replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
    println("cc".indexOf('c'))
    println("cc".lastIndexOf('c'))
    println("".isBlank())
    println("".isEmpty())
}