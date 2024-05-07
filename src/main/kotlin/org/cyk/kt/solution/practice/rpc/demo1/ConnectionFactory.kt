package org.cyk.kt.solution.practice.rpc.demo1

class ConnectionFactory(
    private val host: String,
    private val port: Int,
) {

    fun newConnection() = Connection(host, port)

}