package org.cyk.solution.question



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

}

fun main() {
}
