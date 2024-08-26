import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Test37Tests {
    @Test
    fun regex() {
        assertTrue(Regex("(^(?=^[■▲○◉□▫▪● ]+!)(([■▲○◉□]+) ?([▫□▪●▲]+) ?)+)!").matches("■▲○◉□▫□▪●◉◉▫▪▪●●□□▲▲○○◉■◉■▲▲□□◉▲!"))
        assertTrue(Regex("(?=abcdefg)abcdefg").matches("abcdefg"))
        assertTrue(Regex("ab+").matches("abbbbb"))
        assertTrue(Regex("(ab)+").matches("abababab"))
        assertTrue(Regex("^(((?=ab|ba)|(?=.$))[abc])+$").matches("abababab"))
    }
}