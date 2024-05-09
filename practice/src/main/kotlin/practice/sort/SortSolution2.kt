package org.cyk.kt.solution.practice.sort

import kotlin.system.measureTimeMillis

//class SortSolution2 {
//
//    fun insertSort(arr: IntArray) {
//        for (i in 1 ..< arr.size) {
//            val key = arr[i]
//            var j = i - 1
//            while (j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j]
//                j--
//            }
//            arr[j + 1] = key
//        }
//    }
//
//    fun selectSort(arr: IntArray) {
//        for (i in arr.indices) {
//            var index = i
//            for (j in i + 1 ..< arr.size) {
//                if (arr[index] > arr[j]) {
//                    index = j
//                }
//            }
//            swap(arr, i, index)
//        }
//    }
//
//    private fun swap(arr: IntArray, i: Int, j: Int) {
//        val tmp = arr[i]
//        arr[i] = arr[j]
//        arr[j] = tmp
//    }
//
//    fun bubbleSort(arr: IntArray) {
//        for (i in arr.indices) {
//            for (j in 0 ..< arr.size - i - 1) {
//                if(arr[j] > arr[j + 1]) {
//                    swap(arr, j , j + 1)
//                }
//            }
//        }
//    }
//
//    fun shellSort(arr: IntArray) {
//        var gap = arr.size
//        while (gap > 1) {
//            gap /= 2
//            shellInsertSort(arr, gap)
//        }
//        shellInsertSort(arr, 1)
//    }
//
//    private fun shellInsertSort(arr: IntArray, gap: Int) {
//        for (i in gap ..< arr.size) {
//            val key = arr[i]
//            var j = i - gap
//            while (j >= 0 && arr[j] > key) {
//                arr[j + gap] = arr[j]
//                j -= gap
//            }
//            arr[j + gap] = key
//        }
//    }
//
//    fun heapSort(arr: IntArray) {
//        createBigHeap(arr)
//        for (i in arr.lastIndex downTo 1) {
//            swap(arr, 0, i)
//            adjustDown(arr, 0, i)
//        }
//    }
//
//    private fun createBigHeap(arr: IntArray) {
//        val len = arr.size
//        for (parent in (len - 1 - 1) / 2 downTo 0) {
//            adjustDown(arr, parent, len)
//        }
//    }
//
//    private fun adjustDown(arr: IntArray, parent: Int, len: Int) {
//        var p = parent
//        var child = p * 2 + 1
//        while (child < len) {
//            if (child + 1 < len && arr[child] < arr[child + 1]) {
//                child++
//            }
//            if (arr[child] > arr[p]) {
//                swap(arr, child, p)
//                p = child
//                child = child * 2 + 1
//            } else  {
//                break
//            }
//        }
//    }
//
//    fun quickSort(arr: IntArray) {
//        quick(arr, 0, arr.lastIndex)
//    }
//
//    private fun quick(arr: IntArray, left: Int, right: Int) {
//        if (left >= right) return
//        //寻找一个基准
//        val point = fundPoint(arr, left, right)
//    }
//
//    private fun fundPoint(arr: IntArray, left: Int, right: Int): Int {
//        var l = left
//        var r = right
//        val key = arr[l]
//        while (l < r) {
//            while (l < r && arr[r] >= key) {
//                r--
//            }
//            arr[l] = arr[r]
//            while (l < r && arr[l] <= key) {
//                l++
//            }
//            arr[r] = arr[l]
//        }
//        arr[l] = key
//        return l
//    }
//
//}
//
//fun main() {
//    val s = SortSolution2()
//    val arr = intArrayOf(3,1,2,5,4,6,1)
//    s.heapSort(arr)
//    println(arr.contentToString())
//}