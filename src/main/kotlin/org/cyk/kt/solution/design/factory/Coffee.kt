package org.cyk.solution.design.factory

//抽象产品
abstract class Coffee {

    abstract fun getInfo(): String

    fun addSugar() = println("加糖")

    fun addWeight() = println("加量")

}

//具体产品
class ACoffee: Coffee() {

    override fun getInfo(): String {
        addSugar()
        addWeight()
        return "咖啡A"
    }

}

//具体产品
class BCoffee: Coffee() {

    override fun getInfo(): String {
        addSugar()
        addWeight()
        return "咖啡B"
    }

}

