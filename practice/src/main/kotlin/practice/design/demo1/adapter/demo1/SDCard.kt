package org.cyk.solution.design.adapter.demo1

interface SDCard {

    fun read(): String
    fun write(msg: String)

}

class SDCardImpl: SDCard {

    override fun read(): String {
        return "SD msg"
    }

    override fun write(msg: String) {
        println("[write SD] msg: $msg")
    }

}