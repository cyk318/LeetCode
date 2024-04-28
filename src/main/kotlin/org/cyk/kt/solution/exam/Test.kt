package org.cyk.kt.solution.exam

import java.util.*


//fun main(args: Array<String>) {
//    val read = Scanner(System.`in`)
//    while(read.hasNext()) {
//        val str = read.next()
//        val result = str.replace("mei", "tuan")
//        println(result)
//    }
//}

//fun main() {
//    val read = Scanner(System.`in`)
//    while(read.hasNext()) {
//        val row = read.nextInt()
//        val col = read.nextInt()
//        val arr = Array(row) { CharArray(col) }
//        for (i in 0 until row) {
//            val strRow = read.next()
//            for (j in 0 until col) {
//                arr[i][j] = strRow[j]
//            }
//        }
//
//        var count = 0
//        for (i in 0 until row - 1) {
//            for (j in 0 until col - 1) {
//                if(!isValid(arr, i, j)) {
//                    count++
//                }
//            }
//        }
//        println(count)
//    }
//}
//
//fun isValid(arr: Array<CharArray>, i: Int, j: Int): Boolean {
//    val set = mutableSetOf<Char>()
//    set.add(arr[i][j])
//    if(set.contains(arr[i + 1][j])) return true
//    set.add(arr[i + 1][j])
//    if(set.contains(arr[i][j + 1])) return true
//    set.add(arr[i][j + 1])
//    if(set.contains(arr[i + 1][j + 1])) return true
//    set.add(arr[i + 1][j + 1])
//    return false
//}


private var result = 0
fun main() {
    val read = Scanner(System.`in`)
    while(read.hasNext()) {
        val size = read.nextInt()
        val k = read.nextInt()
        val nums = IntArray(size)
        var sum = 0
        for (i in nums.indices) {
            nums[i] = read.nextInt()
            sum += nums[i]
        }
        if(sum < k) {
            println(0)
            continue
        }
        dfs(nums, k)
        println(result)
    }
}

fun dfs(nums: IntArray, k: Int) {
    if (nums.size < 2) return
    for (i in 0 until nums.size - 1) {
        if (nums.size < 2) return
        val numsTmp = nums.copyOfRange(i + 1, nums.size)
        numsTmp[i] = nums[i] + nums[i + 1]
        if(isValid(numsTmp, k)) {
            result++
        } else {
            continue
        }
        dfs(numsTmp, k)
    }
}

fun isValid(numsTmp: IntArray, k: Int): Boolean {
    for (num in numsTmp) {
        if(num < k) return false
    }
    return true
}

