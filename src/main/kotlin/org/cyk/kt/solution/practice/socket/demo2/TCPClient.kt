package org.cyk.kt.solution.practice.socket.demo2

import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner

class TCPClient(
    ip: String,
    port: Int,
) {

    private val socket = Socket(ip, port)

    fun start() {
        val systemScan = Scanner(System.`in`)
        socket.getInputStream().use { input ->
            socket.getOutputStream().use { output ->
                val serverScan = Scanner(input)
                val writer = PrintWriter(output)
                while (true) {
                    print("client -> ")
                    val request = systemScan.next()
                    writer.println(request)
                    writer.flush()
                    val response = serverScan.next()
                    println("server -> $response")
                }
            }
        }
    }

}

fun main() {
    val client = TCPClient("127.0.0.1", 9000)
    client.start()
}