package practice.design.demo2

//抽象产品
interface Coffee {

    fun make()

}

//具体产品 A
class CoffeeA: Coffee {

    override fun make() {
        //这里是一个复杂的过程...
        println("制作 A 咖啡")
    }

}

//具体产品 B
class CoffeeB: Coffee {

    override fun make() {
        //这里是一个复杂的过程...
        println("制作 B 咖啡")
    }

}

//抽象工厂
interface CoffeeFactory {

    fun createCoffee(): Coffee

}

//具体工厂 A
class CoffeeAFactory: CoffeeFactory {

    override fun createCoffee(): CoffeeA {
        return CoffeeA()
    }

}

//具体工厂B
class CoffeeBFactory: CoffeeFactory {

    override fun createCoffee(): CoffeeB {
        return CoffeeB()
    }

}

fun main() {
    val aFactory = CoffeeAFactory()
    val result = aFactory.createCoffee()
    println(result.make())
}

