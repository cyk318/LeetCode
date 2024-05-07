package org.cyk.solution.sort

import java.util.Random
import kotlin.random.asKotlinRandom

//class SortSolution {
//
//    fun insertSort(arr: Array<Int>) {
//        for(i in 1 until arr.size) {
//            val key = arr[i]
//            var j = i - 1
//            while(j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j]
//                j--
//            }
//            arr[j + 1] = key
//        }
//    }
//
//    private fun shellInsert(arr: Array<Int>, gap: Int) {
//        for(i in gap until arr.size) {
//            val key = arr[i]
//            var j = i - gap
//            while(j >= 0 && arr[j] > key) {
//                arr[j + gap] = arr[j]
//                j -= gap
//            }
//            arr[j + gap] = key
//        }
//    }
//
//    fun shellSort(arr: Array<Int>) {
//        var gap = arr.size
//        while(gap > 1) {
//            gap /= 2
//            shellInsert(arr, gap)
//        }
//        shellInsert(arr, 1)
//    }
//
//    fun selectSort(arr: Array<Int>) {
//        for(i in arr.indices) {
//            var minIndex = i
//            for(j in i + 1 until arr.size) {
//                if(arr[minIndex] > arr[j]) {
//                    minIndex = j
//                }
//            }
//            swap(arr, minIndex, i)
//        }
//    }
//
//    private fun swap(arr: Array<Int>, minIndex: Int, i: Int) {
//        val tmp = arr[minIndex]
//        arr[minIndex] = arr[i]
//        arr[i] = tmp
//    }
//
//    fun bubbleSort(arr: Array<Int>) {
//        for(i in arr.indices) {
//            for(j in 0 until arr.size - 1 - i) {
//                if(arr[j] > arr[j + 1]) {
//                    val tmp = arr[j]
//                    arr[j] = arr[j + 1]
//                    arr[j + 1] = tmp
//                }
//            }
//        }
//    }
//
//    fun heapSort(arr: Array<Int>) {
//        createBigHeap(arr)
//        var end = arr.size - 1
//        while(end > 0) {
//            swap(arr, 0, end)
//            adjustDownTree(arr, 0, end)
//            end--
//        }
//    }
//
//    private fun createBigHeap(arr: Array<Int>) {
//        val len = arr.size
//        //从最后一个父节点开始起，向下调整
//        for(parent in (len - 1 - 1) / 2 downTo 0) {
//            adjustDownTree(arr, parent, len)
//        }
//    }
//
//    private fun adjustDownTree(arr: Array<Int>, parentFinal: Int, len: Int) {
//        var parent = parentFinal
//        var child = parent * 2 + 1
//        while(child < len) {
//            if(child + 1 < len && arr[child + 1] > arr[child]) {
//                child++
//            }
//            if(arr[child] > arr[parent]) {
//                swap(arr, child, parent)
//                parent = child
//                child = child * 2 + 1
//            } else {
//                break
//            }
//        }
//    }
//
//    fun quickSort(arr: Array<Int>) {
//        quickDfs(arr, 0, arr.size - 1)
//    }
//
//    private fun quickDfs(arr: Array<Int>, left: Int, right: Int) {
//        if(left >= right) {
//            return
//        }
//        if(right - left + 1 <= 7) {
//            quickInsert(arr, left, right)
//        }
//        val midIndex = funMidIndex(arr, left, right)
//        swap(arr, left, midIndex)
//        val point = fundPoint(arr, left, right)
//        quickDfs(arr, left, point - 1)
//        quickDfs(arr, point + 1, right)
//    }
//
//    private fun quickInsert(arr: Array<Int>, left: Int, right: Int) {
//        for(i in left + 1 until  right + 1) {
//            val key = arr[i]
//            var j = i - 1
//            while(j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j]
//                j--
//            }
//            arr[j + 1] = key
//        }
//    }
//
//    private fun funMidIndex(arr: Array<Int>, left: Int, right: Int): Int {
//        val mid = (left + right) / 2
//        if(arr[left] < arr[right]) {
//            return if(arr[left] > arr[mid]) {
//                left
//            } else if(arr[right] < arr[mid]) {
//                right
//            } else {
//                mid
//            }
//        } else {
//            return if(arr[left] < arr[mid]) {
//                left
//            } else if(arr[right] > arr[mid]) {
//                right
//            } else {
//                mid
//            }
//        }
//    }
//
//    private fun fundPoint(arr: Array<Int>, left: Int, right: Int): Int {
//        var l = left
//        var r = right
//        val key = arr[l]
//        while(l < r) {
//            while(l < r && key <= arr[r]) {
//                r--
//            }
//            arr[l] = arr[r]
//            while(l < r && arr[l] <= key) {
//                l++
//            }
//            arr[r] = arr[l]
//        }
//        arr[l] = key
//        return l
//    }
//
//    fun mergeSort(arr: Array<Int>) {
//        mergeFun(arr, 0, arr.size - 1)
//    }
//
//    private fun mergeFun(arr: Array<Int>, left: Int, right: Int) {
//        if(left >= right) {
//            return
//        }
//        val mid = (left + right) / 2
//        mergeFun(arr, left, mid)
//        mergeFun(arr, mid + 1, right)
//        merge(arr, left, right, mid)
//    }
//
//    private fun merge(arr: Array<Int>, left: Int, right: Int, mid: Int) {
//        val tmpArr = Array(right - left + 1) { 0 }
//        var index = 0
//        var s1 = left
//        var s2 = mid + 1
//        while(s1 <= mid && s2 <= right) {
//            if(arr[s1] < arr[s2]) {
//                tmpArr[index++] = arr[s1++]
//            } else {
//                tmpArr[index++] = arr[s2++]
//            }
//        }
//        while(s1 <= mid) {
//            tmpArr[index++] = arr[s1++]
//        }
//        while(s2 <= right) {
//            tmpArr[index++] = arr[s2++]
//        }
//        for(i in 0 until index) {
//            arr[left + i] = tmpArr[i]
//        }
//    }
//
//}
//
//
//const val dataSize = 100_0000
//
//fun testSpeed() {
//    val s = SortSolution()
//    val arr = Array(dataSize) { 0 }
//    for(i in 0 until dataSize) {
//        arr[i] = Random().asKotlinRandom().nextInt(0, dataSize)
//    }
//    val sTime = System.currentTimeMillis()
//    s.quickSort(arr)
//    val eTime = System.currentTimeMillis()
//    println("100 万数据排序耗时: ${eTime - sTime}")
//}
//
//fun main() {
//    val arr = arrayOf(3,1,2,4,6,5,9,7,8,5)
//    val s = SortSolution()
//    s.mergeSort(arr)
////    println(arr.contentToString())
//    testSpeed()
//}
