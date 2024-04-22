package org.cyk.solution.thread.demo1

fun main() {
    val t = Thread {
        println("通过匿名内部类，创建 Thread 子类")
    }
    t.start()
}