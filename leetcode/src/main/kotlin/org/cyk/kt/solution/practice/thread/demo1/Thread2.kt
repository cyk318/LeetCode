package org.cyk.solution.thread.demo1
class MyRunnable: Runnable {
    override fun run() {
        println("实现 Runnable 接口，重写 run 方法")
    }
}

fun main() {
    val t = Thread(MyRunnable())
    t.start()
}