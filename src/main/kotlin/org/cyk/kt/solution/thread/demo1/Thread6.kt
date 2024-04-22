package org.cyk.solution.thread.demo1

import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class MyCall: Callable<Int> {
    override fun call(): Int {
        println("实现 Callable 接口，重写 call 方法")
        return 1
    }
}

fun main() {
    val task = FutureTask(MyCall())
    val t = Thread(task)
    t.start()
}