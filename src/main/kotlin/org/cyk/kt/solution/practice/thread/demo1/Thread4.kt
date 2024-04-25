package org.cyk.solution.thread.demo1

fun main() {
    val t = Thread(Runnable() {
        println("通过匿名内部类，实现 Runnable 接口，重写 run 方法")
    })
    t.start()
}