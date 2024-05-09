package org.cyk.solution.thread.demo1

import java.util.concurrent.Executors

fun main() {
    val pool1 = Executors.newCachedThreadPool()
    pool1.submit(Runnable {
        println("通过创建线程池来创建线程1")
    })

    val pool2 = Executors.newFixedThreadPool(4)
    pool2.submit(Runnable {
        println("通过创建线程池来创建线程2")
    })

    val pool3 = Executors.newSingleThreadExecutor()
    pool3.submit(Runnable {
        println("通过创建线程池来创建线程3")
    })
}