package org.cyk.kt.solution.leetcode

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

}

fun main() {

}