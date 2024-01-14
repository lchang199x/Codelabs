package test

fun main() {
    val arr1 = intArrayOf(1, 1, 1)
    val arr2 = intArrayOf(1, 1, 3, 2, 1, 5, 3)
    val list1 = arr1.filter { it != 1 }
    val list2 = arr2.filter { it != 1 }
    println(list1)
    println(list2)

    // 注意Char的toInt()不是转化为数字，而是转化为Char的ASCII编码值，即code扩展属性
    println('9'.toInt())
    println('9'.code)
    // Char转化为字符串的方法，注意kotlin里面String没有String.valueOf方法，另字符串拼接'9' + ""也可
    println('9'.toString().toInt())
    // 将非十进制数的字符串表示转化为十进制数的方法，支持任意禁止向十进制的转换
    println(Integer.parseInt("11", 2)) // 输出3
    println(Integer.parseInt("119", 10))
}