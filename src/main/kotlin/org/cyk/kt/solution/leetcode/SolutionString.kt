package org.cyk.kt.solution.leetcode

import org.cyk.kt.solution.exam.reverse

class SolutionString {

    //1.反转字符串
    fun reverseString(s: CharArray): Unit {
        s.reverse()
        var left = 0
        var right = s.size - 1
        while (left < right) {
            val tmp = s[left]
            s[left] = s[right]
            s[right] = tmp
            left++
            right--
        }
    }

    //2.反转字符串 II
    fun reverseStr(s: String, k: Int): String {
        val len = s.length
        val chs = s.toCharArray()
        val k2 = k * 2
        for (i in 0 ..< len step k2) {
            if (i + k < len) {
                reverseRange(chs, i, i + k - 1)
            } else {
                reverseRange(chs, i, len - 1)
            }
        }
        return String(chs)
    }

    private fun reverseRange(chs: CharArray, start: Int, end: Int) {
        var s = start
        var e = end
        while (s < e) {
            val tmp = chs[s]
            chs[s] = chs[e]
            chs[e] = tmp
            s++
            e--
        }
    }

    //3.找出字符串中第一个匹配项的下标
    fun strStr(haystack: String, needle: String): Int {
        var left = 0
        var right = needle.length
        while (right <= haystack.length) {
            if (haystack.substring(left, right) == needle) {
                return left
            }
            left++
            right++
        }
        return -1
    }

}

fun main() {
    val str = "abcdefg"
    val s = SolutionString()
    s.reverseStr(str, 3)
}