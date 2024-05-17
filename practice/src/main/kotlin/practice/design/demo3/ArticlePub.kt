package practice.design.demo3

enum class ArticlePubType (
    val code: Int
) {

    UNKNOWN(0),
    NORMAL(1),
    PRIVATE(2),
    TIMER(3),
    ;

}

data class Article(
    val id: Long,
    val title: String,
    val content: String,
)

data class ArticleDto (
    val id: Long,
    val title: String,
    val content: String,
    val type: Int, //发布类型
)

/**
 * 文章发布策略
 */
interface ArticlePubStrategy {

    fun pub(o: Article)

}

/**
 * 普通发布
 */
class NormalPubStrategy: ArticlePubStrategy {

    override fun pub(o: Article) {
        /**
         * 普通的逻辑
         */
        println("normal pub")
    }

}

class PrivatePubStrategy: ArticlePubStrategy {

    override fun pub(o: Article) {
        println("private pub")
    }


}

class TimerPubStrategy: ArticlePubStrategy {

    override fun pub(o: Article) {
        println("timer pub")
    }

}

/**
 * post 的请求数据一般放在 body 中，所以前端传入的一般是一个 json 数据，此时后端必须要使用 对象来接收
 * get 的请求数据一般放在 query string 中，因此直接用零散参数接收即可
 */

fun main() {

}