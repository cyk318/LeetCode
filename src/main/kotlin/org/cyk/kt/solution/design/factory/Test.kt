package org.cyk.solution.design.factory

fun main() {
    val factoryA = ACoffeeFactory()
    val factoryB = BCoffeeFactory()

    //创建咖啡A
    val coffeeA = factoryA.create()
    println(coffeeA.getInfo())

    //创建咖啡B
    val coffeeB = factoryB.create()
    println(coffeeB.getInfo())
}