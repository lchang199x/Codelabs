package test

val validCharacters = listOf("零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "百", "千", "万")
val hanzi = listOf('零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十')

private fun Char.hanziToDigit(): Int = hanzi.indexOf(this)

private fun List<String>.calculateSum(): Int {
    var sum = 0
    this.forEach {
        if (it.isEmpty()) return@forEach
        sum += when (it.last()) {
            '千' -> it.first().hanziToDigit() * 1000
            '百' -> it.first().hanziToDigit() * 100
            '十' -> it.first().hanziToDigit() * 10
            else -> it.first().hanziToDigit()
        }
    }
    return sum
}

fun main() {
    println(convert("万万"))
}

fun convert(param: String?): String {
    if (param.isNullOrBlank()) return "NAN"
    param.forEach { if (!validCharacters.contains("$it")) return "NAN" }
    if (param.length == 1) return if (hanzi.contains(param.first())) "${param.first().hanziToDigit()}" else "NAN"
    if (param.startsWith('零') || param.endsWith('零') || param.contains("零零")) return "NAN"

    val temp = if (param.startsWith('十')) "一$param" else param
    val input = temp + if (Regex(""".*百[一二三四五六七八九]""").matches(temp)) {
        '十'
    } else if (Regex(""".*千[一二三四五六七八九]""").matches(temp)) {
        '百'
    } else if (Regex(""".*万[一二三四五六七八九]""").matches(temp)) {
        '千'
    } else ""

    val base = """([一二三四五六七八九]千)?(零|[一二三四五六七八九]百)?(零|[一二三四五六七八九]十)?([一二三四五六七八九])?"""
    val pattern = """(($base)万)?$base"""

    val matched = Regex(pattern).matchEntire(input) ?: return run {
        println("don't match: $input")
        "NAN"
    }
    val result1 = matched.groupValues.subList(3, 7)
    val result2 = matched.groupValues.subList(matched.groupValues.size - 4, matched.groupValues.size)

    println("matched: $result1, $result2")

    return "${result1.calculateSum() * 10000 + (result2.calculateSum())}"
}
