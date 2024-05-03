package org.cyk.solution.question

import javax.lang.model.type.ArrayType
import kotlin.math.min


class SolutionArray {

    //1.二分查找
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

    //2.移除元素
//    fun removeElement(nums: IntArray, `val`: Int): Int {
//        val len = nums.size
//        //有两种解决思路，一种是搬运元素，比较耗时，还有一种是使用 空间换时间
//        val list = mutableListOf<Int>()
//        for (i in 0 ..< len) {
//            if (nums[i] != `val`) list.add(nums[i])
//        }
//        for (i in 0 ..< list.size) {
//            nums[i] = list[i]
//        }
//        return list.size
//    }
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var result = 0
        for (i in nums.indices) {
            if (nums[i] != `val`) nums[result++] = nums[i]
        }
        return result
    }

    //3.有序数组的平方
    fun sortedSquares(nums: IntArray): IntArray {
        var left = 0
        var rignt = nums.size - 1
        val result = IntArray(nums.size)
        var index = nums.size - 1
        while (left <= rignt) {
            if (nums[left] * nums[left] < nums[rignt] * nums[rignt]) {
                result[index--] = nums[rignt] * nums[rignt]
                rignt--
            } else {
                result[index--] = nums[left] * nums[left]
                left++
            }
        }
        return result
    }

    //4.长度最小的子数组
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var result = Int.MAX_VALUE
        for (i in nums.indices) {
            var sum = nums[i]
            if (sum >= target) return 1
            for (j in i - 1 downTo 0) {
                sum += nums[j]
                if (sum >= target) {
                    result = min(i - j + 1, result)
                    break
                }
            }
        }
        return if (Int.MAX_VALUE == result) 0 else result
    }

    //5.螺旋矩阵 II
    fun generateMatrix(n: Int): Array<IntArray> {
        val arr = Array(n) { IntArray(n) }
        var up = 0
        var down = n - 1
        var left = 0
        var right = n - 1
        var index = 1
        while (up < down && left < right) {
            for (i in left .. right) {
                arr[up][i] = index++
            }
            up++

            for (i in up .. down) {
                arr[i][right] = index++
            }
            right--

            for (i in right downTo left) {
                arr[down][i] = index++
            }
            down--

            for (i in down downTo up) {
                arr[i][left] = index++
            }
            left++
        }
        if (n % 2 == 1) {
            val ans = n / 2
            arr[ans][ans] = index
        }
        return arr
    }

}

fun main() {
}
