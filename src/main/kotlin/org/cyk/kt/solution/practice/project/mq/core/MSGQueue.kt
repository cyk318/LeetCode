package org.cyk.kt.solution.practice.project.mq.core

import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.LinkedBlockingQueue

//函数式接口
typealias ConsumerHandler = (message: Message) -> Unit

//消费者
class ConsumerEnv(
    val id: String = UUID.randomUUID().toString(),
    val queueName: String,
    val consumer: ConsumerHandler,
)

data class MSGQueue(
    val name: String,
) {

    private val msgQueue = LinkedBlockingQueue<Message>()
    private val consumerEnvList = LinkedBlockingDeque<ConsumerEnv>()
    private val consumerPool = Executors.newFixedThreadPool(4)

    init {
        Thread {
            while (true) {
                //1.取消息
                val msg = msgQueue.take()
                //2.取消费者
                val consumerEnv = chooseConsumerEnv()
                //3.消费消息
                consumerPool.submit {
                    consumerEnv.consumer(msg)
                }
            }
        }.start()
    }

    /**
     * 模拟实现轮询获取消费者
     */
    private fun chooseConsumerEnv(): ConsumerEnv {
        val consumerEnv = consumerEnvList.takeLast()
        consumerEnvList.offerFirst(consumerEnv)
        return consumerEnv
    }

    fun addMsg(message: Message) {
        msgQueue.offer(message)
    }

    fun addConsumer(consumerEnv: ConsumerEnv) {
        consumerEnvList.offerFirst(consumerEnv)
    }

}