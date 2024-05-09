package org.cyk.kt.solution.practice.rpc.demo1

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.lang.RuntimeException
import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors

class BrokerServer(
    port: Int
) {

    private val socket = ServerSocket(port)
    private val clientPool = Executors.newFixedThreadPool(5)

    //key: channelId ,value: Socket
    //注意：这里的 Channel 只表示一个 "逻辑" 上的连接(创建，销毁 channel)，这个 Map 是为了后台信息统计
    private val channelSession = ConcurrentHashMap<String, Socket>()

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
        println("[BrokerServer] 客户端上线！ip: ${client.inetAddress}, port: ${client.port}")
        try {
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
        } catch (e: EOFException) {
            println("[BrokerServer] 客户端正常下线！ip: ${client.inetAddress}, port: ${client.port}")
        } catch (e: Exception) {
            println("[BrokerServer] 客户端连接异常！ip: ${client.inetAddress}, port: ${client.port}")
        } finally {
            client.close()
            removeChannelSession(client)
        }
    }

    private fun process(request: Request, client: Socket) = with(request) {
        //1.解析请求
        val req = BinaryTool.bytesToAny(payload)
        //2.获取请求中的 channelId，记录和 Socket 的关系(让每个 channel 都对应自己的 Socket，类似于 Session)
        val reqBase = req as ReqBaseArguments
        //3.根据 type 类型执行不同的服务(创建 Channel、销毁 Channel、创建交换机、删除交换机...)
        val ok = when(type) {
            1 -> {
                channelSession[reqBase.channelId] = client
                println("[BrokerServer] channel 创建成功！channelId: ${reqBase.channelId}")
                true
            }
            2 -> {
                channelSession.remove(reqBase.channelId)
                println("[BrokerServer] channel 销毁成功！channelId: ${reqBase.channelId}")
                true
            }
            3 -> virtualHost.exchangeDeclare(req as ExchangeDeclareReq)
            4 -> virtualHost.exchangeDelete(req as ExchangeDeleteReq)
            5 -> virtualHost.queueDeclare(req as QueueDeclareReq)
            //...
            else -> throw RuntimeException("[BrokerServer] 客户端请求 type 非法！type: $type")
        }
        //4.返回响应
        val respBase = RespBaseArguments(reqBase.rid, reqBase.channelId, ok)
        val payload = BinaryTool.anyToBytes(respBase)
        Response(type, payload.size, payload)
    }

    /**
     * 读取客户端请求
     * 使用 DataInputStream 的主要原因就是有多种读取方式，例如 readInt()、readLong()，这些都是原生 InputStream 没有的
     */
    private fun readRequest(dataInputStream: DataInputStream) = with(dataInputStream) {
        val type = readInt()
        val length = readInt()
        val payload = ByteArray(length)
        val n = read(payload)
        if (n != length) throw RuntimeException("[BrokerServer] 读取客户端请求异常！")
        Request(type, length, payload)
    }

    /**
     * 将响应写回给客户端
     */
    private fun writeResponse(response: Response, outputStream: DataOutputStream) = with(outputStream) {
        writeInt(response.type)
        writeInt(response.length)
        write(response.payload)
        flush()
    }

    //删除所有和这个 clientSocket 有关的 Channel
    private fun removeChannelSession(client: Socket) {
        val channelIdList = mutableListOf<String>()
        //这里不能直接删除，会破坏迭代器结构
        for (entry in channelSession) {
            if (entry.value == client) channelIdList.add(entry.key)
        }
        for (channelId in channelIdList) {
            channelSession.remove(channelId)
        }
    }

}



