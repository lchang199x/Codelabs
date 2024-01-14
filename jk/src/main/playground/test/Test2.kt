package test

/**
 * kotlin位运算通过infix中缀调用语法实现，没有使用运算符重载
 */
fun main() {
    println(0b00001010 shl 2)
    println(0b00001010 shr 2)
    // unsigned shift right
    println(0b00001010 ushr 2)
    println(0b00001010 and 2)
    println(0b00001010 or 2)
    println(0b00001010 xor 2)
    // 非运算，相当于java中的 ~10
    println(0b00001010.inv())

    println(0b00001010 shr 1 and  1)
    // 算术运算符+比位运算符shr优先级高
    println(0b00001010 shr 1 + 1)
    println(0b00001010 shr (1 + 1))
    // 优先级取余%大于加法+，取余是和乘法*和除法/一档的
    println(1 + 1 % 2)
    println(1 + (1 % 2))
    println((1 + 1) % 2)
    println(0b00001010 shl 1 + 1 % 2)
    println(0b00001010 shl (1 + 1 % 2))
    println((0b00001010 shl 1) + (1 % 2))
}