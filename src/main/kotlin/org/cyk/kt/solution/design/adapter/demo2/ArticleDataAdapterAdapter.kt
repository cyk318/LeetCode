package org.cyk.solution.design.adapter.demo2

//@Component 表示这是个组件
class ArticleDataAdapter ( //构造方法注入
    val infoRepo: ArticleInfoRepo,
    val statRepo: ArticleStatRepo
): ArticleDataHandler {

    override fun queryArticleById(id: Long): ArticleVo {
        val info = infoRepo.queryById(id)
        val stat = statRepo.queryById(id)
        return map(info, stat)
    }


    override fun pageArticleById(offset: Int, limit: Int): PageResp<ArticleVo> {
        val pageInfo = infoRepo.pageInfo(offset, limit)
        val pageStat = statRepo.pageStat(offset, limit)
        return map(pageStat, pageStat)
    }

    private fun map(info: PageResp<ArticleStat>, stat: PageResp<ArticleStat>): PageResp<ArticleVo> {
        TODO("Not yet implemented")
    }

    private fun map(info: ArticleInfo, stat: ArticleStat): ArticleVo {
        TODO("Not yet implemented")
    }

}