package org.cyk.solution.thread.demo1

fun main() {
    val t = Thread {
        println("通过 lambda 表达式创建")
    }
    t.start()
}