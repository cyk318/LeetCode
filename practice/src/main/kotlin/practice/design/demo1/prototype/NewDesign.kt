package practice.design.demo1.prototype

data class Pig(
    var name: String,
    val age: Int,
    val color: String,
    val child: PigChild
) {

    /**
     * kotlin 的数据类默认提供了 copy() 方法，用于实现对这个对象的 浅拷贝
     * 但如果在 copy 时传入其他参数，就会为当前对象创建新对象，但是对内的引用变量还是传入地址(Pig 的 hashCode 不一致，单 child 的hashCode 一致)
     * 例如，复制当前对象并修改 name
     */
    fun copyWithId(name: String): Pig {
        return this.copy(name = name)
    }

}

data class PigChild (
    var name: String,
)

fun main() {
    val p = Pig("lyj", 1, "pink", PigChild("aaa"))
    val p2 = p.copy("ll")

    println("${p.hashCode()}, ${p.child.hashCode()}")
    println("${p2.hashCode()}, ${p2.child.hashCode()}")
}


