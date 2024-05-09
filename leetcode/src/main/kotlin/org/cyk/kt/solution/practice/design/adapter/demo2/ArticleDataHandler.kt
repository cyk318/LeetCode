package org.cyk.solution.design.adapter.demo2



interface ArticleDataHandler {

    fun queryArticleById(id: Long): ArticleVo

    fun pageArticleById(offset: Int, limit: Int): PageResp<ArticleVo>

    //......

}

data class ArticleVo (
    val id: Long,
    val title: String,
    val content: String,
    val pv: Long,
    val likeCnt: Long ,
    val collectCnt: Long,
    val commentCnt: Long,
)

class PageResp<T>

