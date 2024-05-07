package org.cyk.kt.solution.practice.rpc.demo1

import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class Channel(
    private val channelId: String,
    private val connection: Connection,  //自己当前属于哪个 Channel
) {

    //key: rid(为了能让每个 Channel 对应上自己的响应)
    //value: RespBaseArguments(具体的响应)
    //当 Connection 的扫描线程接收到响应之后，就会将响应传给这个 map
    private val ridRespMap = ConcurrentHashMap<String, RespBaseArguments>()
    //这个锁是用来阻塞等待服务端响应的(避免轮询)，当服务端传来响应时，Connection 就会唤醒锁
    private val locker = Object()

    private fun generateRid() = "R-${UUID.randomUUID()}"

    private fun waitResp(rid: String): RespBaseArguments {
        val respBase: RespBaseArguments
        while (ridRespMap[rid] == null) { // 如果为空，说明此时服务端还没有传来响应
            synchronized(locker) { //为了避免轮询，就让其阻塞等待
                locker.wait()
            }
        }
        //出了这个循环，那么 ridRespMap[rid] 一定不为空
        return ridRespMap[rid]!!
    }

    fun notifyResp(respBase: RespBaseArguments) {
        ridRespMap[respBase.rid] = respBase
        synchronized(locker) {
            //当前也不直到有多少线程在等待响应，就全部唤醒
            locker.notifyAll()
        }
    }

    /**
     * 创建 Channel
     */
    fun createChannel(): Boolean {
        //1.创建基本请求
        val reqBase = ReqBaseArguments(
            rid = generateRid(),
            channelId = channelId
        )
        //2.构造 TCP 通信请求
        val payload = BinaryTool.anyToBytes(reqBase)
        val req = Request(
            type = 1,
            length = payload.size,
            payload = payload
        )
        //3.发送请求
        connection.writeReq(req)
        //4.等待客户端响应
        val respBase = waitResp(reqBase.rid)
        return respBase.ok
    }

    fun removeChannel(): Boolean {
        //1.创建基本请求
        val reqBase = ReqBaseArguments(
            rid = generateRid(),
            channelId = channelId
        )
        //2.构造 TCP 通信请求
        val payload = BinaryTool.anyToBytes(reqBase)
        val req = Request(
            type = 2,
            length = payload.size,
            payload = payload
        )
        //3.发送请求
        connection.writeReq(req)
        //4.等待客户端响应
        val respBase = waitResp(reqBase.rid)
        return respBase.ok
    }

    fun exchangeDeclare(
        name: String,
        type: ExchangeType,
        durable: Boolean,
        autoDelete: Boolean,
        arguments: MutableMap<String, Any>,
    ): Boolean {
        val exchangeDeclareReq = ExchangeDeclareReq(
            name = name,
            type = type,
            durable = durable,
            autoDelete = autoDelete,
            arguments = arguments,
            rid = generateRid(),
            channelId = channelId,
        )
        val payload = BinaryTool.anyToBytes(exchangeDeclareReq)
        val req = Request(
            type = 3,
            length = payload.size,
            payload = payload,
        )
        connection.writeReq(req)
        val respBase = waitResp(exchangeDeclareReq.rid)
        return respBase.ok
    }

    fun exchangeDelete(name: String): Boolean {
        val exchangeDeleteReq = ExchangeDeleteReq(
            name = name,
            rid = generateRid(),
            channelId = channelId,
        )
        val payload = BinaryTool.anyToBytes(exchangeDeleteReq)
        val req = Request(
            type = 4,
            length = payload.size,
            payload = payload,
        )
        connection.writeReq(req)
        val respBase = waitResp(exchangeDeleteReq.rid)
        return respBase.ok
    }

    fun queueDeclare(
        name: String,
        durable: Boolean,
        exclusive: Boolean,
        autoDelete: Boolean,
        arguments: MutableMap<String, Any>,
    ): Boolean {
        val queueDeclareReq = QueueDeclareReq(
            name = name,
            durable = durable,
            exclusive = exclusive,
            autoDelete = autoDelete,
            arguments = arguments,
            rid = generateRid(),
            channelId = channelId,
        )
        val payload = BinaryTool.anyToBytes(queueDeclareReq)
        val req = Request(
            type = 5,
            length = payload.size,
            payload = payload,
        )
        connection.writeReq(req)
        val resp = waitResp(queueDeclareReq.rid)
        return resp.ok
    }

}