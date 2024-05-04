package org.cyk.kt.solution.practice.project.mq.test

import org.cyk.kt.solution.practice.project.mq.core.ConsumerEnv


fun main() {

    val consumer1 = ConsumerEnv(
        queueName = "queue1",
        consumer = {
            println("我是消费者1，我消费了消息: $it")
        }
    )
//    host.addConsumerEnv(consumer1)

//    while (true) {
//        Thread.sleep(100000)
//    }
}