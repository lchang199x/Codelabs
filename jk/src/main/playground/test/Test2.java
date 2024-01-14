package test;

/**
 * 计算机中整数都是以补码形式表示，位运算都是在补码上进行的
 *
 * 求补码：原码 => 反码（符号位之外取反） => 补码（加1）
 * 求原码：补码 => 反码 (符号位之外取反) => 原码（加1） 由补码求源码，其实就是对补码再求一次补码
 *
 * 求反码不带符号位，但是强制转换时的截断和非运算都是带符号位
 */
public class Test2 {
    public static void main(String[] args) {
        // 10
        System.out.println((byte)0b00001010);
        // -10
        System.out.println((byte)-0b00001010);
        // -118 int型正整数补码直接截断 10001010 反码 11110101 原码 11110110 => -118
        System.out.println((byte)0b10001010);
        // 138 > Byte.MAX_VALUE
        System.out.println(0b10001010);
        // 118
        System.out.println((byte)-0b10001010);
        // -128 为了一一映射，人为规定补码1000000表示-128，它没有原码和反码（求原码会溢出）
        System.out.println(Byte.MIN_VALUE);
        // 127 补码 & 原码 01111111 => 2^7 -1
        System.out.println(Byte.MAX_VALUE);
        // -2^31
        System.out.println(Integer.MIN_VALUE);
        // 2^31 - 1
        System.out.println(Integer.MAX_VALUE);

        // 左移：左侧直接丢弃，右侧直接补0
        System.out.println((byte)0b00001010 << 1);
        // -10的反码 11110101 补码11110110 左移1位右边填充0 11101100 反码 10010011 原码 10010100 => -20
        System.out.println((byte)-0b00001010 << 1);

        // 正数右移：右侧直接丢弃，左侧补0
        // 10 ÷ 2 = 5
        System.out.println((byte)0b00001010 >> 1);
        // 5 ÷ 2 = 2
        System.out.println((byte)0b00001010 >> 2);
        // 2 ÷ 2 = 1
        System.out.println((byte)0b00001010 >> 3);
        // 1 ÷ 2 = 0
        System.out.println((byte)0b00001010 >> 4);

        // 负数右移：右侧直接丢弃，左侧补1
        // -10 ÷ 2 = -5
        // -10的反码 11110101 补码11110110 右移1位左边填充符号位1 11111011 反码 10000100 原码10000101 => -5
        System.out.println((byte)-0b00001010 >> 1);
        // -5 ÷ 2 = -3
        // -5的反码 11111010 补码11111011 右移1位左边填充符号位1 11111101 反码 10000010 原码10000011 => -3
        System.out.println((byte)-0b00001010 >> 2);
        // -2 ÷ 2 = -2
        System.out.println((byte)-0b00001010 >> 3);
        // -1 ÷ 2 = -1
        System.out.println((byte)-0b00001010 >> 4);

        // 无符号右移
        // 5
        System.out.println((byte)0b00001010 >>> 1);
        // 2147483643
        System.out.println((byte)-0b00001010 >>> 1);
        // -10的反码 11110101 补码11110110 非运算变为正数 00001001 即是补码 & 原码 => 9
        System.out.println(~(byte)-0b00001010);

        // java中字符类型和int型相加，字符型会先转化为int型，结果也是int型
        // 而kotlin中Char类利用运算符重载定义了plus(other: Int): Char方法，所以kotlin中字符类型和int型相加，结果还是字符类型
        System.out.println('a' + 10000);
    }
}
