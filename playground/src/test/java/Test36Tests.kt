import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ktest.convert

class Test36Tests {
    @Test
    fun null_blank_input() {
        assertEquals("NAN", convert(""))
        assertEquals("NAN", convert(null))
        assertEquals("NAN", convert("  "))
    }

    @Test
    fun invalid_character_input() {
        assertEquals("NAN", convert("啦啦啦"))
        assertEquals("NAN", convert("0"))
    }

    @Test
    fun one_digit_input() {
        assertEquals("0", convert("零"))
        assertEquals("1", convert("一"))
        assertEquals("2", convert("二"))
        assertEquals("3", convert("三"))
        assertEquals("4", convert("四"))
        assertEquals("5", convert("五"))
        assertEquals("6", convert("六"))
        assertEquals("7", convert("七"))
        assertEquals("8", convert("八"))
        assertEquals("9", convert("九"))
        assertEquals("10", convert("十"))
    }

    @Test
    fun error_composition() {
        assertEquals("NAN", convert("零万"))
        assertEquals("NAN", convert("二零"))
        assertEquals("NAN", convert("一千零零三"))
        assertEquals("NAN", convert("百"))
        assertEquals("NAN", convert("千"))
        assertEquals("NAN", convert("万"))
        assertEquals("NAN", convert("一百千"))
        assertEquals("NAN", convert("二十十一"))
    }

    @Test
    fun valid_input() {
        assertEquals("1325023", convert("一百三十二万五千零二十三"))
        assertEquals("1000000", convert("一百万"))
        assertEquals("1000001", convert("一百万零一"))
        assertEquals("5230", convert("五千二百三十"))
        assertEquals("1015000", convert("一百零一万五千"))
    }

    @Test
    fun valid_input_omit() {
        assertEquals("10", convert("十"))
        assertEquals("10", convert("一十"))
        assertEquals("13", convert("十三"))
        assertEquals("13", convert("一十三"))
        assertEquals("23", convert("二十三"))
        assertEquals("130", convert("一百三"))
        assertEquals("130", convert("一百三十"))
        assertEquals("1300", convert("一千三"))
        assertEquals("1300", convert("一千三百"))
        assertEquals("13000", convert("一万三"))
        assertEquals("13000", convert("一万三千"))
        assertEquals("105320", convert("十万五千三百二"))
        assertEquals("105320", convert("一十万五千三百二十"))
    }
}