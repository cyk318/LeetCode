package practice.design.demo1.prototype

data class Pig(
    val id: Int,
    var name: String,
    val age: Int,
    val color: String,
): Cloneable {

    /**
     * JDK 中提供的 Cloneable 的 clone 是对当前对象的 浅拷贝
     */
    public override fun clone(): Any {
        return super.clone()
    }

    /**
     * kotlin 的数据类默认提供了 copy() 方法，用于实现对这个对象的 深拷贝
     * 可以完全复制，也可以根据提供的属性进行复制
     * 例如，复制当前对象并修改 id
     */
    fun cloneWithId(id: Int): Pig {
        return this.copy(id = id)
    }

}

fun main() {
    val pig = Pig(1, "lyj", 3, "粉色")

    //通过 jdk 提供的 clone 实现浅拷贝
    val j1 = pig.clone() as Pig
    val j2 = pig.clone() as Pig
    val j3 = pig.clone() as Pig

    println("${j1.id}, ${j1.name}, ${j1. color}, ${j1.hashCode()}")
    println("${j2.id}, ${j2.name}, ${j2. color}, ${j2.hashCode()}")
    println("${j3.id}, ${j3.name}, ${j3. color}, ${j3.hashCode()}")

    println("-----------分割线------------")

    //通过 kotlin 中 数据类的默认方法实现深拷贝
    val k1 = pig.cloneWithId(2)
    val k2 = pig.cloneWithId(3)
    val k3 = pig.cloneWithId(4)


    println("${k1.id}, ${k1.name}, ${k1. color}, ${k1.hashCode()}")
    println("${k2.id}, ${k2.name}, ${k2. color}, ${k2.hashCode()}")
    println("${k3.id}, ${k3.name}, ${k3. color}, ${k3.hashCode()}")

}


