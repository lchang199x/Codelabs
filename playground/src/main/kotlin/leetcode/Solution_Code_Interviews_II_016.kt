package leetcode

import kotlin.math.max

/**
 * 不含重复字符的最长子字符串
 * [](https://leetcode-cn.com/problems/wtcaE1/)
 * [](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)
 */
class Solution_Code_Interviews_II_016 {
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) return 0
        var result = 0
        val set = hashSetOf<Char>()
        var j = -1
        for (i in s.indices) {
            if (i != 0) set.remove(s[i - 1])
            while (j + 1 < s.length && !set.contains(s[j + 1])) {
                set.add(s[j + 1])
                j++
            }
            result = max(result, j - i + 1)
        }

        return result
    }
}

fun main() {
    println(Solution_Code_Interviews_II_016().lengthOfLongestSubstring("abcabcbb"))
}