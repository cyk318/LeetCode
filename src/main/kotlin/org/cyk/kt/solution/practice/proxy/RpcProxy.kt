package org.cyk.kt.solution.practice.proxy

import com.fasterxml.jackson.databind.ObjectMapper

data class User(
    val id: Long,
    val username: String,
)

interface UserDao {

    fun findUserById(id: Long): User

}

//抽象主题(此处为 Feign 客户端)
interface UserRpcService {

    //@GetMapper
    fun findUserById(id: Long): String

}

//具体主题(此处为 User 微服务的服务提供者)
//@Service
class UserRpcServiceImpl(
    val userDao: UserDao,
    val mapper: ObjectMapper,
): UserRpcService {

    override fun findUserById(id: Long): String {
        val result = userDao.findUserById(id)
        //此处 Feign 底层会自动将 result 序列化成 json 格式
        return mapper.writeValueAsString(result)
    }

}

//代理类(此处为 其他微服务处理 User Feign 客户端返回的 Json 数据)
//@Service
class UserRpcProxy(
    val userRpcService: UserRpcService,
    val mapper: ObjectMapper,
) {

    fun findUserById(id: Long): User {
        val json = userRpcService.findUserById(id)
        return mapper.readValue(json, User::class.java)
    }

}

