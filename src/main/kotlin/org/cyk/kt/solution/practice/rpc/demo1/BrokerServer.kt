package org.cyk.kt.solution.practice.rpc.demo1

import java.io.DataInputStream
import java.io.DataOutputStream
import java.lang.RuntimeException
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.Executors

class BrokerServer(
    port: Int
) {

    private val socket = ServerSocket(port)
    private val clientPool = Executors.newFixedThreadPool(5)
    //key: channelId ,value: Socket
    //注意：这里的 Channel 只表示一个 "逻辑" 上的连接(创建，销毁 channel)，服务端这边此时没有体现出实质性的作用
    private val socketMap = mutableMapOf<String, Socket>()
    private val virtualHost = VirtualHost()

    fun start() {
        println("[BrokerServer] 启动！")
        while (true) {
            val client = socket.accept()
            clientPool.submit {
                clientProcess(client)
            }
        }
    }

    private fun clientProcess(client: Socket) {
        client.getInputStream().use { inputStream ->
            client.getOutputStream().use { outputStream ->
                DataInputStream(inputStream).use { dataInputStream ->
                    DataOutputStream(outputStream).use { dataOutputStream ->
                        while (true) {
                            val request = readRequest(dataInputStream)
                            val response = process(request, client)
                            writeResponse(response, dataOutputStream)
                        }
                    }
                }
            }
        }
    }

    private fun process(request: Request, client: Socket) = request.run {
        //1.解析请求
        val req = BinaryTool.bytesToAny(payload)
        //2.获取请求中的 channelId，记录和 Socket 的关系(让每个 channel 都对应自己的 Socket，类似于 Session)
        val reqBase = req as ReqBaseArguments
        //3.根据 type 类型执行不同的服务
        val ok = when(type) {
            1 -> virtualHost.exchangeDeclare(req as ExchangeDeclareReq)
            2 -> virtualHost.exchangeDelete(req as ExchangeDeleteReq)
            3 -> virtualHost.queueDeclare(req as QueueDeclareReq)
            //...
            else -> throw RuntimeException("[BrokerServer] 客户端请求 type 非法！type: $type")
        }
        //4.返回响应
        val respBase = RespBaseArguments(reqBase.rid, reqBase.channelId, ok)
        Response(type, length, BinaryTool.anyToBytes(respBase))
    }

    /**
     * 读取客户端请求
     * 使用 DataInputStream 的主要原因就是有多种读取方式，例如 readInt()、readLong()，这些都是原生 InputStream 没有的
     */
    private fun readRequest(dataInputStream: DataInputStream) = dataInputStream.run {
        val type = readInt()
        val length = readInt()
        val payload = ByteArray(length)
        val n = read(payload)
        if (n != length) throw RuntimeException("[BrokerServer] 读取客户端请求异常！")
        Request(type, length, payload)
    }

    private fun writeResponse(response: Response, outputStream: DataOutputStream) = outputStream.run {
        writeInt(response.type)
        writeInt(response.length)
        write(response.payload)
        flush()
    }

}