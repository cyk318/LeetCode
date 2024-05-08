package org.cyk.kt.solution.practice.rpc.demo1

enum class ExchangeType(
    code: Int
) {

    DIRECT(0),
    FANOUT(1),
    TOPIC(2),

}