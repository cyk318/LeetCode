package practice.design.demo4

//抽象产品
interface Car {

    fun start()
    fun stop()
    fun honk()

}

//具体产品
class SUV: Car {

    override fun start() {
        println("SUV start ...")
    }

    override fun stop() {
        println("SUV stop ...")
    }

    override fun honk() {
        println("SUV honk ...")
    }

}

//具体产品
class BMW: Car {

    override fun start() {
        println("BMW start ...")
    }

    override fun stop() {
        println("BMW stop ...")
    }

    override fun honk() {
        println("BMW honk ...")
    }

}

interface CarFactory {
    fun createCar(): Car
}

class SUVFactory: CarFactory {

    override fun createCar() = SUV()

}

class BMWFactory: CarFactory {

    override fun createCar() = BMW()

}

fun main() {
    val suvFactory = SUVFactory()
    val suv = suvFactory.createCar()
    suv.start()
    suv.honk()
    suv.stop()

    val bmwFactory = BMWFactory()
    val bmw = bmwFactory.createCar()
    bmw.start()
    bmw.honk()
    bmw.stop()
}