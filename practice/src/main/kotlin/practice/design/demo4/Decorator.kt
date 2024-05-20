package practice.design.demo4

////抽象组件
//interface Coffee {
//
//    fun getDesc(): String
//    fun getCost(): Double
//
//}
//
////具体组件
//class SimpleCoffee: Coffee {
//
//    override fun getDesc(): String = "[ simple coffee ]"
//    override fun getCost(): Double = 6.0
//
//}
//
////抽象装饰者
//abstract class CoffeeDecorator(
//    protected val coffee: Coffee
//): Coffee {
//
//    override fun getDesc(): String = coffee.getDesc()
//    override fun getCost(): Double = coffee.getCost()
//
//}
//
////具体装饰者
//class MikeDecorator(
//    coffee: Coffee
//): CoffeeDecorator(coffee) {
//
//    override fun getDesc(): String = "${coffee.getDesc()}, mike"
//    override fun getCost(): Double = coffee.getCost() + 2.0
//
//}
//
////具体装饰者
//class SugarDecorator(
//    coffee: Coffee
//): CoffeeDecorator(coffee) {
//
//    override fun getDesc(): String = "${coffee.getDesc()}, sugar"
//    override fun getCost(): Double = coffee.getCost() + 1.0
//
//}
//
//fun main() {
//    val simpleCoffee = SimpleCoffee()
//    println("simpleCoffee:")
//    println("desc: ${simpleCoffee.getDesc()}")
//    println("cost: ${simpleCoffee.getCost()}")
//
//    //加奶
//    val mikeSimpleCoffee = MikeDecorator(simpleCoffee)
//    println("mikeSimpleCoffee:")
//    println("desc: ${mikeSimpleCoffee.getDesc()}")
//    println("cost: ${mikeSimpleCoffee.getCost()}")
//
//    //加糖
//    val mikeSugarSimpleCoffee = SugarDecorator(mikeSimpleCoffee)
//    println("mikeSugarSimpleCoffee:")
//    println("desc: ${mikeSugarSimpleCoffee.getDesc()}")
//    println("cost: ${mikeSugarSimpleCoffee.getCost()}")
//
//}

//抽象组件
interface Coffee {

    fun getDesc(): String
    fun getCost(): Double
}

//具体组件
class SimpleCoffee: Coffee {

    override fun getDesc(): String = "[simple coffee]"
    override fun getCost(): Double = 6.0

}

abstract class CoffeeDecorator(
    private val coffee: Coffee
): Coffee {

    override fun getDesc(): String = coffee.getDesc()

    override fun getCost(): Double  = coffee.getCost()

}

class MikeDecorator(
    private val coffee: Coffee
): Coffee {

    override fun getDesc(): String = coffee.getDesc() + ", mike"

    override fun getCost(): Double = coffee.getCost() + 2.0

}

class SugarDecorator(
    private val coffee: Coffee
): Coffee {

    override fun getDesc(): String = coffee.getDesc() + ", sugar"

    override fun getCost(): Double = coffee.getCost() + 1.0

}

fun main() {
    val simpleCoffee = SimpleCoffee()
    println("simpleCoffee: ")
    println("desc: ${simpleCoffee.getDesc()}")
    println("cost: ${simpleCoffee.getCost()}")

    val mikeSimpleCoffee = MikeDecorator(simpleCoffee)
    println("mikeSimpleCoffee: ")
    println("desc: ${mikeSimpleCoffee.getDesc()}")
    println("cost: ${mikeSimpleCoffee.getCost()}")

}


