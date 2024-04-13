package test

fun main() {
    // 使用向前查找子表达式，用于定位/查找它前面的模式，此时向前查找子表达式不包含在最终的匹配结果中
    Regex("([A-Z]-)(?=[a-z]{3})([a-z0-9]* )").findAll("< A-xyz37 # B-ab6142 # C-Wxy66 # D-qrs93 ")
        .forEach {
            println(it.groupValues)
        }
    // \1是实际匹配的重复而不是模式的重复，故此处不匹配，只有x1x才匹配
    Regex("([xy]).*\\1").findAll("x1x")
        .forEach {
            println(it.groupValues)
        }
    // 以下模式和\bx[a-z]*y\b是等价的，即可以用前后查找实现单词边界一样的效果
    // 注意嵌套的向后查找是因为开头可能有很多非单词字符，而向后查找不支持[^a-z]+这样变长的模式，必须是固定长度，为确保matchEntire要用嵌套的向后查找
    Regex("(?<=^|(?<=[^a-z]))x[a-z]+y(?=$|[^a-z])").findAll("expurgatory xylometer xenotime    xenomorphically")
        .forEach {
            println(it.groupValues)
        }
    // 配对使用的场景，如<与>配对，那它俩中间的字符就不能是>，故应该写作<[^>]+>，另kotlin Regex暂不支持条件处理如(?(1)\s*</a>)
    Regex("""<a\s+[^>]+>\s*<img\s+[^>]+></a>""").findAll(""""<a HREF="/home"><img SRC="/images/home.gif"></a>""")
        .forEach {
            println(it.groupValues)
        }
    // 前后查找属于非捕获组，
    Regex("(^(?=^[■▲○◉□▫▪● ]+!)((([■▲○◉□]+) ?([▫□▪●▲]+) ?)+))!").findAll("■▲○◉□▫□▪●◉◉▫▪▪●●□□▲▲○○◉■◉■▲▲□□◉▲!")
        .forEach {
            println(it.groupValues)
        }
    // 深入理解向前查找，Y其实是查找一个位置，不对应捕获组，但(?=pattern)的pattern部分可包含捕获组
    Regex("abc(?=defg)").findAll("abcdefg")
        .forEach {
            println(it.groupValues)
        }
    // 深入理解向前查找，Y其实是查找一个位置（零宽度字符），和其前后的模式部分不是拼接关系
    Regex("(?=abcdefg)abc").findAll("abcdefg")
        .forEach {
            println(it.groupValues)
        }
    // 深入理解向前查找，Y其实是查找一个位置
    Regex("(?=.*cde)a").findAll("abcdefg")
        .forEach {
            println(it.groupValues)
        }
    // 深入理解向前查找，Y其实是查找一个位置
    Regex("(?=.*cde)b").findAll("abcdefg")
        .forEach {
            println(it.groupValues)
        }
    // 深入理解向前查找，Y其实是查找一个位置，此处位置可以在a、b、c之前，但不能在d、e、f之前
    Regex("(?=.*cde)f").findAll("abcdefg")
        .forEach {
            println(it.groupValues)
        }
    // 深入理解子表达式，对于子表达式的重复匹配，每次重复都会创建一个新的捕获，但整个字符串匹配完成后，捕获组记住的是其最后一次匹配的内容g
    Regex("([abcdefg])+").findAll("abcdefg")
        .forEach {
            println(it.groupValues)
        }
    // 深入greedy匹配，对于.+(?!b).+，如果(?!b)匹配失败, 第一个 .+ 就会试着匹配一个更短的字符串, 然后再尝试匹配后续部分
    Regex(".+(?!b).+").findAll("bbbabb")
        .forEach {
            println(it.groupValues)
        }
    // {双层转义\\{，向前查找不捕获，+仅对{[1-6]:([1-6])}生效
    Regex("((\\{[1-6]:([1-6])\\})(?=$|\\{\\3))+\$").findAll("{1:3}{3:3}{3:6}{6:1}{1:3}{3:3}{3:3}")
        .forEach {
            println(it.groupValues)
        }
    // 一个常见模式：向前查找限定字符集合每个字符的位置，然后对这个限定的字符集合重复，并加上边界匹配
    // [abcd]-->(a|b|c|d)-->(a(?=$|[bc])|b(?=$|[bc])|c(?=$|d)|d(?=$|a))
    Regex("^(A(?=$|[BC])|B(?=$|[BC])|C(?=$|D)|D(?=$|A))+$").findAll("ABCDABB")
        .forEach {
            println(it.groupValues)
        }
    // 向前查找限定字符集合某两个字符必须才能同时出现，然后对这个限定的字符集合重复，并加上边界匹配
    Regex("^(((?=LL|Lu|LF|HH|Hd|HF|uH|dL|FH|FL)|(?=.$))[LHudF])+$").findAll("LuHHHdLuHFLLLFHdLLLLFHHdLLu")
        .forEach {
            println(it.groupValues)
        }
    // 继续感受一下子表达式内部的向前查找指定可能的组合/绑定关系，注意这里^限定以\1作为开头，不是限定(?=ab|ba)开头或(?=ab|ba)|(?=.$)开头
    Regex("^(((?=ab|ba)|(?=.\$))[abc])+\$").findAll("ababab")
        .forEach {
            println(it.groupValues)
        }
}