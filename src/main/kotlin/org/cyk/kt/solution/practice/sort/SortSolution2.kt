package org.cyk.kt.solution.practice.sort

class SortSolution2 {

    fun insertSort(arr: IntArray) {
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

    fun selectSort(arr: IntArray) {
        for (i in arr.indices) {
            var index = i
            for (j in i + 1 ..< arr.size) {
                if (arr[index] > arr[j]) {
                    index = j
                }
            }
            swap(arr, i, index)
        }
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

    fun bubbleSort(arr: IntArray) {
        for (i in arr.indices) {
            for (j in 0 ..< arr.size - i - 1) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j , j + 1)
                }
            }
        }
    }

    fun shellSort(arr: IntArray) {
        var gap = arr.size
        while (gap > 1) {
            gap /= 2
            shellInsertSort(arr, gap)
        }
        shellInsertSort(arr, 1)
    }

    private fun shellInsertSort(arr: IntArray, gap: Int) {
        for (i in gap ..< arr.size) {
            val key = arr[i]
            var j = i - gap
            while (j >= 0 && arr[j] > key) {
                arr[j + gap] = arr[j]
                j -= gap
            }
            arr[j + gap] = key
        }
    }


}

fun main() {
    val s = SortSolution2()
    val arr = intArrayOf(3,1,2,5,4,6,1)
    s.shellSort(arr)
    println(arr.contentToString())
}