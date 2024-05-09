package org.cyk.kt.solution.practice.timer

//import java.util.concurrent.Executors
//import java.util.concurrent.PriorityBlockingQueue
//
//data class MyTask(
//    val task: Runnable,
//    var time: Long,
//): Comparable<MyTask> {
//
//    init {
//        this.time += System.currentTimeMillis()
//    }
//
//    override fun compareTo(other: MyTask): Int {
//        return (this.time - other.time).toInt()
//    }
//
//}
//
//class MyTimer {
//
//    private val queue = PriorityBlockingQueue<MyTask>()
//    private val pool = Executors.newCachedThreadPool()
//    private val locker = Object()
//
//    init {
//        Thread {
//            while(true) {
//                synchronized(locker) {
//                    val myTask = queue.take()
//                    val curTime = System.currentTimeMillis()
//                    val taskTime = myTask.time
//                    if(curTime >= taskTime) {
//                        pool.submit { myTask.task.run() }
//                    } else {
//                        queue.put(myTask)
//                        locker.wait(taskTime - curTime)
//                    }
//                }
//            }
//        }.start()
//    }
//
//    fun submit(task: Runnable, time: Long) {
//        queue.put(MyTask(task, time))
//        synchronized(locker) {
//            locker.notify()
//        }
//    }
//
//}
//
//fun main() {
//    val timer = MyTimer()
//
//    timer.submit({
//        println("任务1")
//    }, 1000)
//    timer.submit({
//        println("任务3")
//    }, 3000)
//    timer.submit({
//        println("任务4")
//    }, 4000)
//    timer.submit({
//        println("任务2")
//    }, 2000)
//}
