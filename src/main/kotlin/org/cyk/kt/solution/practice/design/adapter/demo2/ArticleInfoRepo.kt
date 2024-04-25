package org.cyk.solution.design.adapter.demo2


data class ArticleInfo(
    val id: Long,
    val title: String,
    val content: String,
)

//这里的实现就是对数据库的操作
interface ArticleInfoRepo {

    fun queryById(id: Long): ArticleInfo

    fun pageInfo(offset: Int, limit: Int): PageResp<ArticleInfo>

}