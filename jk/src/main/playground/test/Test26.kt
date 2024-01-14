package test

// kotlin语法注意：括号是否成对，main方法里不要随便用return
// until→通常可以indices代替，size length都是字段，而不是size(), length()方法调用
class Test26 {
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        var result = 0
        val set = hashSetOf<Char>()
        var j = 0

        for (i in s.indices) {
            if (i != 0) set.remove(s[i - 1])
            while (j < s.length && !set.contains(s[j])) {
                set.add(s[j])
                j++
            }
            result = result.coerceAtLeast(set.size)
        }

        return result
    }
}