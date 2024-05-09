package org.cyk.kt.solution.practice.socket.demo2

import java.io.IOException
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.util.Scanner
import java.util.concurrent.Executors

class TCPServer(
    port: Int
) {

    private val socket = ServerSocket(port)
    private val pool = Executors.newCachedThreadPool()

    fun start() {
        println("[TCPServer] 启动！")
        while (true) {
            val clientSocket = socket.accept()
            pool.submit {
                processClient(clientSocket)
            }
        }
    }

    private fun processClient(clientSocket: Socket) {
        println("[TCPServer] 客户端上线！ip: ${clientSocket.inetAddress}, port: ${clientSocket.port}")
        try {
            clientSocket.getInputStream().use { input ->
                clientSocket.getOutputStream().use { output ->
                    val scanner = Scanner(input)
                    val writer = PrintWriter(output)
                    while (true) {
                        val request = scanner.next()
                        val response = process(request)
                        writer.println(response)
                        writer.flush()
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            clientSocket.close()
            println("[TCPServer] 客户端下线！ip: ${clientSocket.inetAddress}, port: ${clientSocket.port}")
        }
    }

    private fun process(request: String): String {
        return request
    }

}

fun main() {
    val server = TCPServer(9000)
    server.start()
}