package practice.design.demo1.prototype

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

//data class Pig(
//    val name: String,
//    val age: Int,
//    val color: String,
//    val child: PigChild
//) {
//
//    fun deepClone(): Pig {
//        return Pig(
//            name = this.name,
//            age = this.age,
//            color = this.color,
//            child = child.clone(),
//        )
//    }
//
//}
//
//data class PigChild (
//    var name: String,
//) {
//    fun clone(): PigChild {
//        return PigChild(this.name)
//    }
//}
//
//fun main() {
//    val p = Pig("lyj", 1, "pink", PigChild("aaa"))
//
//    val p2 = p.deepClone()
//
//    println("${p.hashCode()}, ${p.child.hashCode()}")
//    println("${p2.hashCode()}, ${p2.child.hashCode()}")
//}

data class Child(
    val name: String
): Serializable

data class Parent(
    val name: String,
    val child: Child
): Serializable {

    fun deepCopy(): Parent {
        //序列化
        val bo = ByteArrayOutputStream()
        ObjectOutputStream(bo).use { it.writeObject(this) }
        //反序列化
        val bi = ByteArrayInputStream(bo.toByteArray())
        return ObjectInputStream(bi).use { it.readObject() as Parent }
    }

}

fun main() {
    val p = Parent("John", Child("New York"))

    val p1 = p.deepCopy()

    println(p == p1) //true
    println(p === p1) //false
    println(p.child === p1.child) //false
}
