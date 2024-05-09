package org.cyk.solution.design.adapter.demo2

data class ArticleStat (
    val id: Long,
    val pv: Long,
    val likeCnt: Long ,
    val collectCnt: Long,
    val commentCnt: Long,
)

//这里的实现就是对数据库的操作
interface ArticleStatRepo {

    fun queryById(id: Long): ArticleStat

    fun pageStat(offset: Int, limit: Int): PageResp<ArticleStat>

}