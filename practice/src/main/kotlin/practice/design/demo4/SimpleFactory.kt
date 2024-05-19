package practice.design.demo4

import java.lang.RuntimeException

////抽象产品
//interface Car {
//
//    fun start()
//    fun stop()
//    fun honk()
//
//}
//
////具体产品
//class SUV: Car {
//
//    override fun start() {
//        println("SUV start ...")
//    }
//
//    override fun stop() {
//        println("SUV stop ...")
//    }
//
//    override fun honk() {
//        println("SUV honk ...")
//    }
//
//}
//
////具体产品
//class BMW: Car {
//
//    override fun start() {
//        println("BMW start ...")
//    }
//
//    override fun stop() {
//        println("BMW stop ...")
//    }
//
//    override fun honk() {
//        println("BMW honk ...")
//    }
//
//}
//
////具体工厂
//class CarFactory {
//
//    companion object {
//        fun createCar(type: String)= type.let {
//            when (it) {
//                "SUV" -> SUV()
//                "BMW" -> BMW()
//                else -> throw RuntimeException("car type undefine!")
//            }
//        }
//    }
//
//}
//
////测试
//fun main() {
//    val sCar = CarFactory.createCar("SUV")
//    sCar.start()
//    sCar.honk()
//    sCar.stop()
//
//    val bCar = CarFactory.createCar("BMW")
//    bCar.start()
//    bCar.honk()
//    bCar.stop()
//}