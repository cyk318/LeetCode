package practice.design.demo1.bridge


interface Phone {
    fun open()
    fun call()
    fun close()
}

class XiaoMi: Phone {
    override fun open() = println("xiaomi open")
    override fun call() = println("xiaomi call")
    override fun close() = println("xiaomi close")
}

class HuaWei: Phone {
    override fun open() = println("huawei open")
    override fun call() = println("huawei call")
    override fun close() = println("huawei close")
}

class IPhone: Phone {
    override fun open() = println("iphone open")
    override fun call() = println("iphone call")
    override fun close() = println("iphone close")
}

abstract class Model (
    protected val phone: Phone //聚合手机
)

class PhonePro (
    phone: Phone
): Model(phone) {
    fun open() {
        super.phone.open()
        println("pro")
    }
    fun close() {
        super.phone.close()
        println("pro")
    }
    fun call() {
        super.phone.call()
        println("pro")
    }
}

class PhoneMini (
    phone: Phone
): Model(phone) {
    fun open() {
        super.phone.open()
        println("mini")
    }
    fun close() {
        super.phone.close()
        println("mini")
    }
    fun call() {
        super.phone.call()
        println("mini")
    }
}

fun main() {
    val huaWeiPro = PhonePro(HuaWei())
    huaWeiPro.call()
    println("-------------------------------")
    val xiaoMiPro = PhonePro(XiaoMi())
    xiaoMiPro.call()
    println("-------------------------------")
    val iphonePro = PhonePro(IPhone())
    iphonePro.call()
}


