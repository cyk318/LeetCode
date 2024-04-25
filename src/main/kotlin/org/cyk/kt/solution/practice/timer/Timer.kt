package org.cyk.kt.solution.practice.timer

import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue

data class MyTask(
    val task: Runnable,
    var time: Long,
): Comparable<MyTask> {

    init {
        time += System.currentTimeMillis()
    }

    override fun compareTo(other: MyTask): Int {
        return (this.time - other.time).toInt()
    }

}

class MyTimer {

    private val queue = PriorityBlockingQueue<MyTask>()
    private val pool = Executors.newCachedThreadPool()
    private val locker = Object()

    init {
        Thread {
            synchronized(locker) {
                while(true) {
                    val task = queue.take()
                    val taskTime = task.time
                    val curTime = System.currentTimeMillis()
                    if(curTime >= taskTime) {
                        pool.submit { task.task.run() }
                    } else {
                        queue.offer(task)
                        locker.wait(taskTime - curTime)
                    }
                }
            }
        }.start()
    }

    fun schedule(task: Runnable, time: Long) {
        queue.offer(MyTask(task, time))
        synchronized(locker) {
            locker.notify()
        }
    }

}

fun main() {
    val timer = MyTimer()
    timer.schedule({
        println("任务3")
    }, 3000)
    timer.schedule({
        println("任务1")
    }, 1000)
    timer.schedule({
        println("任务4")
    }, 4000)
    timer.schedule({
        println("任务2")
    }, 2000)
}