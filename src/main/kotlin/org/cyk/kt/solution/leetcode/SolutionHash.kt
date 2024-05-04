package org.cyk.kt.solution.leetcode

class SolutionHash {

    //1.有效的字母异位词
    fun isAnagram(s: String, t: String): Boolean {
        val map =  mutableMapOf<Char, Int>()
        val sLen = s.length
        val tLen = t.length
        if (sLen != tLen) return false
        for (i in 0 ..< sLen) {
            map[s[i]] = map.getOrDefault(s[i], 0) + 1
            map[t[i]] = map.getOrDefault(t[i], 0) - 1
        }
        for (ch in map) {
            if (ch.value != 0) return false
        }
        return true
    }

//    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
//        val map = IntArray(1001)
//        val result = mutableListOf<Int>()
//        for (i in nums1.indices) {
//            map[nums1[i]] = 1
//        }
//        for (i in nums2.indices) {
//            if (map[nums2[i]] == 1) {
//                result.add(nums2[i])
//                map[nums2[i]] = 0
//            }
//        }
//        return result.toIntArray()
//    }

}

fun main() {

}