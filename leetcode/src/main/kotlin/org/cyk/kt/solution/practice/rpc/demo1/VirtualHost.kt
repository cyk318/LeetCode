package org.cyk.kt.solution.practice.rpc.demo1

class VirtualHost {

    fun exchangeDeclare(req: ExchangeDeclareReq): Boolean {
        //执行业务逻辑
        //...
        println("[VirtualHost] 创建交换机成功！")
        return true
    }

    fun exchangeDelete(req: ExchangeDeleteReq): Boolean {
        //执行业务逻辑
        //...
        println("[VirtualHost] 删除交换机成功！")
        return true
    }

    fun queueDeclare(req: QueueDeclareReq): Boolean {
        //执行业务逻辑
        //...
        println("[VirtualHost] 创建队列成功！")
        return true
    }

}