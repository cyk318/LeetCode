package org.cyk.kt.solution.practice.rpc.demo1

class VirtualHost {

    fun exchangeDeclare(req: ExchangeDeclareReq): Boolean {
        //执行业务逻辑
        //...
        return true
    }

    fun exchangeDelete(req: ExchangeDeleteReq): Boolean {
        //执行业务逻辑
        //...
        return true
    }

    fun queueDeclare(req: QueueDeclareReq): Boolean {
        //执行业务逻辑
        //...
        return true
    }

}