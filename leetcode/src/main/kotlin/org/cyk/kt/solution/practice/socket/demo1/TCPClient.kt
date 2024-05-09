package org.cyk.solution.socket

import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner

class TCPClient(
    ip: String,
    port: Int,
) {

    private val client = Socket(ip, port)

    fun start() {
        client.getInputStream().use { inputStream ->
            client.getOutputStream().use { outputStream ->
                val clientScan = Scanner(System.`in`) //用户输入
                val serverScan = Scanner(inputStream) //服务器输入
                val write = PrintWriter(outputStream)
                while(true) {
                    //1.控制台输入请求
                    print("client req -> ")
                    val request = clientScan.next()
                    //2.请求发送到服务器
                    write.println(request)
                    write.flush()
                    //3.读取服务器响应
                    val response = serverScan.next()
                    //4.处理响应
                    println("server resp -> $response")
                }
            }
        }
    }
}

fun main() {
    val client = TCPClient("127.0.0.1", 9090)
    client.start()
}