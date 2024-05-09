package org.cyk.kt.solution.practice.project.mq.core

import java.util.UUID


//class ConsumerManager {
//
//    //令牌队列: 存放队列名称的
//    private val tokenQueue = LinkedBlockingQueue<String>()
//    private val pool = Executors.newFixedThreadPool(4)
//    private val scanDog: Thread = Thread {
//        while (true) {
//            //1.取出 token 令牌
//            val queueName = tokenQueue.take()
//
//            //2.从根据令牌拿到队列，并通过轮询的方式挑选出一名消费者
//            val queue = queueMap[queueName] ?: throw RuntimeException("[ConsumerManager]: 队列名称不存在！queueName: $queueName")
//            val consumerEnv = queue.chooseConsumer()
//
//            //3.开始消费
//            consumerEnv?.let { env ->
//                queue.pollMsg()?.let { msg ->
//                    pool.submit{ env.consumer(msg) }
//                }
//            }
//        }
//    }
//
//    init {
//        scanDog.start()
//    }
//
//    /**
//     * 消息通知: 提供一个令牌，告知消费者们，可以拿着这个令牌去消费一个消息了
//     * 此处的一个令牌就对应一个消息
//     */
//    fun msgNotify(queueName: String) {
//        tokenQueue.offer(queueName)
//    }
//
//    /**
//     * 消费者通知: 新的消费者来了，就去看看队列中还有没有消息，有就全消费了
//     */
//    fun consumerNotify() {
//
//    }
//
//}
