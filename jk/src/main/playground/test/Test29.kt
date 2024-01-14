package test

import java.math.BigDecimal

/**
 * 单词decimal是小数的意思，相对于整数integer而言
 */
fun main() {
    // 输出0.1000000000000000055511151231257827021181583404541015625
    val decimal1 = BigDecimal(0.1)
    // 这个才会输出0.1，所以金融、加解密等高精度要求的地方要用字符串传递参数
    val decimal2 = BigDecimal("0.1")
    println(decimal1)
    println(decimal2)
    // 格式化输出
    println(String.format("%.6f", decimal1))
    System.out.printf("%.6f", decimal1)
}