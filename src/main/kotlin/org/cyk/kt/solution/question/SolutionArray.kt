package org.cyk.solution.question

import java.util.BitSet

fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.lastIndex
    while(left < right) {
        val mid = (left + right) / 2
        if(target < nums[mid]) {
            right = mid - 1
        } else if (target > nums[mid]) {
            left = mid + 1
        } else {
            return mid
        }
    }
    return -1
}

fun main() {
    val bitSet = BitSet()
    bitSet.set(12)
    val result1 = bitSet.get(12)
    println(result1)
}
