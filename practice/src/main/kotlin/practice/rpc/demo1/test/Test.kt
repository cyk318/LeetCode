package org.cyk.kt.solution.practice.rpc.demo1.test

import org.cyk.kt.solution.practice.rpc.demo1.BrokerServer
import org.cyk.kt.solution.practice.rpc.demo1.ConnectionFactory
import org.cyk.kt.solution.practice.rpc.demo1.ExchangeType

fun main() {
    val server = BrokerServer(9000)
    server.start()
}
