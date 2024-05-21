package org.cyk.kt.solution.leetcode.demo2

class SolutionArray {

    //1.有多少小于当前数字的数字
    fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
        val len = nums.size
        val tmp = nums.copyOf()
        nums.sort()
        val result = IntArray(len)
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            if (i > 0 && nums[i] != nums[i - 1]) {
                map[nums[i]] = i
            } else if(i == 0) {
                map[nums[i]] = 0
            }
        }
        for (i in nums.indices) {
            result[i] = map[tmp[i]]!!
        }
        return result
    }

}

fun main() {
    val arr = arrayOf(8,1,2,2,3)
    val a = SolutionArray()
    a.smallerNumbersThanCurrent(arr.toIntArray())
}

