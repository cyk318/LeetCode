package org.cyk.kt.solution.exam

import java.lang.StringBuilder
import java.util.*

//fun main(args: Array<String>) {
//    val read = Scanner(System.`in`)
//    while(read.hasNext()) {
//        val n = read.nextLong()
//        println((n - 1L) * n + 1L)
//    }
//}

//fun main(args: Array<String>) {
//    val read = Scanner(System.`in`)
//    while(read.hasNext()) {
//        val n = read.nextInt()
//        val k = read.nextInt()
//        val str = read.next()
//        val sb = StringBuilder(str)
//        for (i in 0 .. n - k) {
//            val revStr = sb.substring(i, i + k).reversed()
//            sb.replace(i, i + k, revStr)
//        }
//        println(sb.toString())
//    }
//}

fun main(args: Array<String>) {
    val read = Scanner(System.`in`)
    while(read.hasNext()) {
        val n = read.nextInt()
        val k = read.nextInt()
        val str = read.next()
        val chs = str.toCharArray()
        for (i in 0 .. n - k) {
            reverse(chs, i, i + k - 1)
        }
        println(String(chs))
    }

}

fun reverse(chs: CharArray, start: Int, end: Int) {
    var s = start
    var e = end
    while (s < e) {
        val tmp = chs[s]
        chs[s] = chs[e]
        chs[e] = tmp
        s++
        e--
    }
}
