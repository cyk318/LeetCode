package org.cyk.kt.solution.practice.rpc.demo1

data class ReqBaseArguments(
    val rid: String,
    val channelId: String,
)

data class CreateUserinfo(
    val reqBaseArguments: ReqBaseArguments,
    val username: String,
    val password: String,
)

data class RespBaseArguments(
    val rid: String,
    val channelId: String,
    val ok: Boolean,
)