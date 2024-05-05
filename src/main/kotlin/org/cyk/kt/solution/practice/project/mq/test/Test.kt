package org.cyk.kt.solution.practice.project.mq.test

import org.cyk.kt.solution.practice.project.mq.core.ConsumerEnv
import org.cyk.kt.solution.practice.project.mq.core.Message
import org.cyk.kt.solution.practice.project.mq.server.VirtualHost



fun main() {
    val host = VirtualHost()

    //消费者订阅队列
    val consumer1 = ConsumerEnv(
        queueName = "queue1",
        consumer = {
            println("我是消费者1，我消费了消息: $it")
        }
    )
    val consumer2 = ConsumerEnv(
        queueName = "queue1",
        consumer = {
            println("我是消费者2，我消费了消息: $it")
        }
    )
    host.addConsumerEnv(consumer1)
    host.addConsumerEnv(consumer2)

    //生产者发送消息
    host.sendMsg("queue1", Message.createMessage("消息1"))
    host.sendMsg("queue1", Message.createMessage("消息2"))
    host.sendMsg("queue1", Message.createMessage("消息3"))
    host.sendMsg("queue1", Message.createMessage("消息4"))
    host.sendMsg("queue1", Message.createMessage("消息5"))
    host.sendMsg("queue1", Message.createMessage("消息6"))

}

