package org.cyk.solution.design.single

import kotlin.concurrent.Volatile

class SingletonLazy {

    companion object {
        @Volatile
        private var instance: SingletonLazy? = null
    }

    fun getInstance(): SingletonLazy {
        instance ?: synchronized(Singleton::class) {
            instance ?: { instance = SingletonLazy() }
        }
        return instance!!
    }

}

class SingletonLazy2 {

    companion object {
        val instance: SingletonLazy2 by lazy { SingletonLazy2() }
    }

}

fun main() {

    val a = SingletonLazy2.instance

}