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

    //2.有效的山脉数组
    fun validMountainArray(arr: IntArray): Boolean {
        val len = arr.size
        if (len < 3) return false
        var index = 0
        if (arr[0] > arr[1]) return false
        while (index < len) {
            if (index > 0 && arr[index] == arr[index - 1]) return false
            if (index > 0 && arr[index] < arr[index - 1]) break
            index++
        }
        if (index == len) return false
        while (index < len) {
            if (arr[index] > arr[index - 1])  return false
            if (arr[index] == arr[index - 1]) return false
            index++
        }
        return true
    }

}

fun main() {
    val arr = arrayOf(0,3,2,1)
    val a = SolutionArray()
    a.validMountainArray(arr.toIntArray())
}

