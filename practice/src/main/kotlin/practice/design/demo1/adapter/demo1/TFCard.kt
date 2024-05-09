package org.cyk.solution.design.adapter.demo1

interface TFCard {

    fun read(): String
    fun write(msg: String)

}

class TFCardImpl: TFCard {

    override fun read(): String {
        return "TF msg"
    }

    override fun write(msg: String) {
        println("[write TF] msg: $msg")
    }

}