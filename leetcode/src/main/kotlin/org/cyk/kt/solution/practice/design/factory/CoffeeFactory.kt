package org.cyk.solution.design.factory

//抽象工厂(接口)
interface CoffeeFactory {

    fun create(): Coffee

}

//具体工厂
class ACoffeeFactory: CoffeeFactory {

    override fun create(): Coffee {
        return ACoffee()
    }

}

//具体工厂
class BCoffeeFactory: CoffeeFactory {

    override fun create(): Coffee {
        return BCoffee()
    }

}