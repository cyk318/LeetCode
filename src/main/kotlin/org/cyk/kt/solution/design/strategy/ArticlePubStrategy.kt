package org.cyk.kt.solution.design.strategy


data class Article (
    val title: String,
    val content: String,
)

//定义发布策略接口
interface ArticlePubStrategy {

    fun publish(article: Article)

}

//普通发布-策略实现
class NormalPubStrategy: ArticlePubStrategy {

    override fun publish(article: Article) {
        //实现普通发布逻辑
        println("normal article save to db! title: ${article.title}")
    }

}

//私密发布-策略实现
class PrivatePubStrategy: ArticlePubStrategy {

    override fun publish(article: Article) {
        //实现私密发布逻辑
        println("private article save to db! title: ${article.title}")
    }

}

//定时发布-策略实现
class TimedPubStrategy: ArticlePubStrategy {

    override fun publish(article: Article) {
        //实现定时发布逻辑
        println("time article save to db! title: ${article.title}")
    }

}

//环境类: 策略上下文，用来选择策略
class ArticlePubContext(
    var articlePubStrategy: ArticlePubStrategy
) {

    fun publish(article: Article) {
        articlePubStrategy.publish(article)
    }

}

fun main() {
    val pubContext = ArticlePubContext(NormalPubStrategy())
    val article = Article(
        title = "Spring | Home",
        content = "Level up your Java™ code. With Spring Boot in your app.",
    )
    //正常发布
    pubContext.publish(article)
    println("=========================================")
    //私密发布
    pubContext.articlePubStrategy = PrivatePubStrategy()
    pubContext.publish(article)
    println("=========================================")
    //定时发布
    pubContext.articlePubStrategy = TimedPubStrategy()
    pubContext.publish(article)
}


