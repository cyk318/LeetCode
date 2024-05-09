package org.cyk.solution.thread.demo1

class MyThead: Thread() {

    override fun run() {
        println("继承 Thread，实现 run 方法")
    }

}

fun main() {
    val t = MyThead()
    t.start()
}