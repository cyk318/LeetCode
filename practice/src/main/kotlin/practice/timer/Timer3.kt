package org.cyk.kt.solution.practice.timer

import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue

data class MyTask(
    val task: Runnable,
    var time: Long,
): Comparable<MyTask> {

    init {
        this.time += System.currentTimeMillis()
    }

    override fun compareTo(other: MyTask): Int {
        return (this.time - other.time).toInt()
    }

}

class MyTime {

    private val queue = PriorityBlockingQueue<MyTask>()
    private val pool = Executors.newCachedThreadPool()
    private val locker = Object()

    init {
        Thread {
            while (true) {
                synchronized(locker) {
                    val myTask = queue.take()
                    val taskTime = myTask.time
                    val curTime = System.currentTimeMillis()
                    if (curTime >= taskTime) {
                        pool.submit { myTask.task.run() }
                    } else {
                        queue.put(myTask)
                        locker.wait(taskTime - curTime)
                    }
                }
            }
        }.start()
    }

    fun schedule(task: Runnable, time: Long) {
        queue.put(MyTask(task, time))
        synchronized(locker) {
            locker.notify()
        }
    }

}

fun main() {
    val timer = MyTime()
    timer.schedule({ println("task2 run ...") }, 2000)
    timer.schedule({ println("task1 run ...") }, 1000)
    timer.schedule({ println("task3 run ...") }, 3000)
}