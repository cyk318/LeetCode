package org.cyk.solution.thread.demo2

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {

    //指定核心线程数目为 3
    val pool = Executors.newScheduledThreadPool(4)

    //用来执行定时方法(和定时器用法一样，这里不演示了)
    //pool.schedule()

    //scheduleAtFixedRate 参数:
    //1.指定一个 Runnable 任务
    //2.初始延迟多久开始执行(此处为 0 s)
    //3.每隔多久执行一次(此处为 5 s)
    //4.单位(此处为 秒)
    val schedule = pool.scheduleAtFixedRate({
        println("这是一个周期性执行的任务")
    }, 0, 5, TimeUnit.SECONDS)

    //主线程延迟 20s
    Thread.sleep(20000)

    //取消任务
    schedule.cancel(true)

    //关闭线程池
    pool.shutdown()

}