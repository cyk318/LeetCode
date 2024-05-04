package org.cyk.kt.solution.practice.project.mq.server

import org.cyk.kt.solution.practice.project.mq.core.ConsumerEnv
import org.cyk.kt.solution.practice.project.mq.core.MSGQueue
import org.cyk.kt.solution.practice.project.mq.core.Message
import java.lang.RuntimeException



class VirtualHost {

    //假设有这些队列
    private val queueMap = mapOf(
        "queue1" to MSGQueue("queue1"),
        "queue2" to MSGQueue("queue2"),
        "queue3" to MSGQueue("queue3"),
    )


    fun sendMsg(queueName: String, message: Message) {
        //将消息添加到队列中
        val queue = queueMap[queueName] ?: throw RuntimeException("[VirtualHost.sendMsg]: 该队列名称不存在！queueName: $queueName")
        queue.addMsg(message)

    }

    fun addConsumerEnv(consumerEnv: ConsumerEnv) {
        //给 xxx队列 添加消费者
        val queue = queueMap[consumerEnv.queueName] ?: throw RuntimeException("[VirtualHost.sendMsg]: 该队列名称不存在！queueName: ${consumerEnv.queueName}")
        queue.addConsumer(consumerEnv)
    }

}