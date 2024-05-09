package org.cyk.solution.socket

import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.Scanner
import java.util.concurrent.Executors

class TCPServer(
    port: Int
) {

    private val serverSocket = ServerSocket(port)

    fun start() {
        println("TCP 服务器启动！")
        while(true) {
            val client = serverSocket.accept()
            val pool = Executors.newCachedThreadPool()
            pool.submit {
                clientHandler(client)
            }
        }
    }

    private fun clientHandler(client: Socket) {
        println("客户端上线: address: ${client.inetAddress}, port: ${client.port}")
        try {
            client.getInputStream().use { inputStream ->
                client.getOutputStream().use { outputStream ->
                    val scanner = Scanner(inputStream)
                    val writer = PrintWriter(outputStream)
                    while(true) {
                        //1.读取请求并解析
                        if(!scanner.hasNext()) {
                            println("客户端下线: address: ${client.inetAddress}, port: ${client.port}")
                        }
                        val request = scanner.next()
                        //2.根据请求计算响应
                        val response = process(request)
                        //3.返回响应
                        writer.println(response)
                        writer.flush()
                        //4.记录日志
                        println("[${client.inetAddress}:${client.port}] req: $request")
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            client.close()
        }
    }

    /**
     * 回显服务
     */
    private fun process(request: String): String {
        return request
    }

}

fun main() {
    val server = TCPServer(9090)
    server.start()
}