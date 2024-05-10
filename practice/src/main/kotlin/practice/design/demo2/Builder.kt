package practice.design.demo2


data class Request(
    val type: Int = 0,
    val length: Int = 0,
    val payload: String = "",
)

fun main() {
    val type: Int? = null
    val length = 1
    val payload = ""

    val req = Request(
        type = type ?: 1
    )
}