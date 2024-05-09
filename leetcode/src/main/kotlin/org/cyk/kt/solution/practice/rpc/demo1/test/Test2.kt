package org.cyk.kt.solution.practice.rpc.demo1.test

import org.cyk.kt.solution.practice.rpc.demo1.ConnectionFactory
import org.cyk.kt.solution.practice.rpc.demo1.ExchangeType

class Test2 {
}

fun main() {
    val factory = ConnectionFactory("127.0.0.1", 9000)
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    val ok1 = channel.createChannel()
    val ok2 = channel.exchangeDeclare("e1", ExchangeType.DIRECT, false, false, mutableMapOf())
    val ok3 = channel.removeChannel()

    println("ok1: $ok1, ok2: $ok2, ok3: $ok3")
}