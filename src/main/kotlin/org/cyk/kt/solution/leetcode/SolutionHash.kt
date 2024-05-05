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

    //2.两个数组的交集
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val map = IntArray(1001)
        val result = mutableListOf<Int>()
        for (i in nums1.indices) {
            map[nums1[i]] = 1
        }
        for (i in nums2.indices) {
            if (map[nums2[i]] == 1) {
                result.add(nums2[i])
                map[nums2[i]] = 0
            }
        }
        return result.toIntArray()
    }

    //3.快乐数
    fun isHappy(n: Int): Boolean {
        val set = mutableSetOf<Int>()
        var num = n
        var sum = 0
        while (sum != 1) {
            var tmp = 0
            while (num != 0) {
                val ans = num % 10
                tmp += ans * ans
                num /= 10
            }
            sum = tmp
            if (set.contains(sum)) return false
            set.add(sum)
            num = sum
        }
        return true
    }

    //6.两数之和
    fun twoSum(nums: IntArray, target: Int): IntArray {
        //key: 值, value: 下标
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            if (map.containsKey(target - nums[i])) {
                return intArrayOf(map[target - nums[i]]!!, i)
            }
            map[nums[i]] = i
        }
        return intArrayOf()
    }

    //7.四数相加II
    fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
        var result = 0
        val map = mutableMapOf<Int, Int>()
        for (i in nums1) {
            for (j in nums2) {
                map[i + j] = map.getOrDefault(i + j, 0) + 1
            }
        }
        for (i in nums3) {
            for (j in nums4) {
                if (map.containsKey(-i - j)) result += map[-i - j]!!
            }
        }
        return result
    }

    //8.赎金信
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val set = IntArray(26) { 0 }
        for (ch in magazine) {
            set[ch - 'a']++
        }
        for (ch in ransomNote) {
            set[ch - 'a']--
            if (set[ch - 'a'] < 0) {
                return false
            }
        }
        return true
    }

}

fun main() {
//    val s = SolutionHash()
//    s.isHappy(19)
}