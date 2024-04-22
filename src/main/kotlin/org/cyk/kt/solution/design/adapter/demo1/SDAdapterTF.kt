package org.cyk.solution.design.adapter.demo1

class SDAdapterTF(
    private val tfCard: TFCard
): SDCard {


    override fun read(): String {
        return tfCard.read()
    }

    override fun write(msg: String) {
        return tfCard.write(msg)
    }

}

fun main() {
    //机器现在只能读取或写入 sd 卡，但是目前手头上只有 tf 卡
    val tfCard = TFCardImpl()
    //因此就需要一个适配器来解决这个问题
    val adapter = SDAdapterTF(tfCard)
    println(adapter.read())
    adapter.write("你好呀")
}