package org.cyk.kt.solution.practice.rpc.demo1

import java.io.DataInputStream
import java.io.DataOutputStream
import java.lang.RuntimeException
import java.net.Socket
import java.net.SocketException
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class Connection(
    ip: String,
    port: Int,
) {

    private val socket = Socket(ip, port)
    private val channelMap = ConcurrentHashMap<String, Channel>()
    //下述这样提前创建好，是为了将来 Channel 在读写请求的时候的方便(Channel 就不用获取输入输出流了)
    private val inputStream = socket.getInputStream()
    private val outputStream = socket.getOutputStream()
    private val dataInputStream = DataInputStream(inputStream)
    private val dataOutputStream = DataOutputStream(outputStream)

    init {
        //此线程负责不停的从服务器这边获取响应
         Thread {
             try {
                 while (!socket.isClosed) {
                     //读取服务器响应
                     val resp = readResp()
                     //将响应交给对应的 Channel
                     putRespToChannel(resp)
                 }
             } catch (e: SocketException) {
                 println("[Connection] 客户端正常断开连接")
             } catch (e: Exception) {
                 println("[Connection] 客户端异常断开连接")
                 e.printStackTrace()
             }
         }.start()
    }


    /**
     * 将客户端 Connection 接收到的请求，交给对应的 Channel 处理(此时 Channel 还在阻塞等待服务端响应)
     */
    private fun putRespToChannel(resp: Response) {
        //这里由于不涉及回调，所以每个 type 类型的响应都长一样，就按照一样的方式解析了
        val baseResp = BinaryTool.bytesToAny(resp.payload) as RespBaseArguments
        val channel = channelMap[baseResp.channelId]
            ?: throw RuntimeException("[Connection] 该响应对应的 Channel 不存在！channelId: ${baseResp.channelId}")
        //将响应交给 Channel
        channel.notifyResp(baseResp)
    }

    /**
     * 创建 Channel
     */
    fun createChannel(): Channel { //1.创建 Channel，保存到 map 种
        val channelId = "C-${UUID.randomUUID()}"
        val channel = Channel(channelId, this)
        channelMap[channelId] = channel
        //2.告知服务端 Channel 创建
        val ok = channel.createChannel()
        //3.如果 Channel 创建不成功，客户端这边也应该要删除对应的 Channel 信息
        if (!ok) channelMap.remove(channelId)
        return channel
    }

    private fun readResp() = with(dataInputStream) {
        val type = readInt()
        val length = readInt()
        val payload = ByteArray(length)
        val n = read(payload)
        if (n != length) throw RuntimeException("[Connection] 客户端读取响应异常！")
        Response(type, length, payload)
    }

    fun writeReq(request: Request) = with(dataOutputStream) {
        writeInt(request.type)
        writeInt(request.length)
        write(request.payload)
        flush()
    }

}