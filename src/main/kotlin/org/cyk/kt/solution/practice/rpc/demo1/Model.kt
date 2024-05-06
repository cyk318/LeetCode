package org.cyk.kt.solution.practice.rpc.demo1

import java.io.Serializable

//Socket 通信请求
data class Request(
    val type: Int,
    val length: Int,
    val payload: ByteArray,
): Serializable

//Socket 通信响应
data class Response(
    val type: Int,
    val length: Int,
    val payload: ByteArray,
): Serializable

//基本参数(每个请求都会携带的参数)
open class ReqBaseArguments(
    open val rid: String = "",
    open val channelId: String = "",
): Serializable

//基本响应参数(每个响应都会携带的参数)，主要是为了应对 mq 回调响应处理
open class RespBaseArguments(
    open val rid: String,
    open val channelId: String,
    open val ok: Boolean,
): Serializable

//主要的请求: 创建交换机、删除交换机、创建队列
data class ExchangeDeclareReq(
    val name: String,
    val type: ExchangeType,
    val durable: Boolean,
    val autoDelete: Boolean,
    val arguments: MutableMap<String, Any>,
    override val rid: String,
    override val channelId: String,
): ReqBaseArguments(), Serializable

data class ExchangeDeleteReq(
    val name: String,
    override val rid: String,
    override val channelId: String,
): ReqBaseArguments(), Serializable

data class QueueDeclareReq(
    val name: String,
    val durable: Boolean,
    val exclusive: Boolean,
    val autoDelete: Boolean,
    val arguments: MutableMap<String, Any>,
    override val rid: String,
    override val channelId: String,
): ReqBaseArguments(), Serializable
