package org.cyk.kt.solution.practice.sort

import org.cyk.kt.solution.leetcode.demo1.SolutionArray

class SortSolution2 {

    fun selectSort(arr: IntArray) {
        for (i in 1 ..< arr.size) {
            val key = arr[i]
            var j = i - 1
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = key
        }
    }



}

fun main() {
    val s = SortSolution2()
    val arr = intArrayOf(3,1,2,5,4,6,1)
    s.selectSort(arr)
    println(arr.contentToString())
}