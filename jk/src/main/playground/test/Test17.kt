package test

fun main() {
    println(isSubnetMaskValid("255.255.252.0"))
    println(isSubnetMaskValid("255.255.253.0"))
    println(isSubnetMaskValid("255.255.0.0"))
    println(isSubnetMaskValid("255.253.0.0"))
    println(isSubnetMaskValid("255.255.255.0"))
    println(isSubnetMaskValid("255.255.255.128"))

}

/**
 * 子网掩码0~255，即可以用8位二进制数表示，四段正好可以放入一个Int型中
 *
 * 对一个数字a，如果a&(a-1)=0，则a是2的某个幂次
 *
 * 合法的子网掩码按位取反+1必然是2的某个幂次
 *
 * java Int位有符号数，255左移24位会溢出，位运算做法不可取
 */
fun isSubnetMaskValidOverFlow(mask: String): Boolean {
    val segs = mask.split(".")
    val n = (segs[0].toInt() shl 24) + (segs[1].toInt() shl 16) + (segs[2].toInt() shl 8) + (segs[3].toInt())
    if (n.inv() and (n.inv() + 1) == 0) {
        return true
    }
    return false
}

/**
 * 判断字符串是否一个合法的子网掩码
 * 防溢出：1. 用字符串的反转代替整数按位取反; 2. 改用Long类型或BigInteger，即"xxxxxx".parseLong()或BigInteger("xxxxxx")
 */
fun isSubnetMaskValid(mask: String): Boolean {
    val segs = mask.split(".")
    val n = segs[0].toBinaryString() + segs[1].toBinaryString() + segs[2].toBinaryString() + segs[3].toBinaryString()
    val realN = n.reversed().parseInt()
    if (realN and (realN + 1) == 0) {
        return true
    }
    return false
}

fun String.toBinaryString(length: Int = 8): String {
    return Integer.toBinaryString(toInt()).padStart(length, '0')
}

fun String.parseInt(radix: Int = 2): Int {
    return Integer.parseInt(this, 2)
}