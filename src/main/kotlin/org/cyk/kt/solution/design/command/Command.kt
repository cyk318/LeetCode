package org.cyk.kt.solution.design.command

import org.cyk.kt.solution.design.strategy.Article

//命令接口
interface ArticleCommand<T> {

    fun execute(obj: T)

}

//具体命令
//@Component 将来可以通过构造方法注入
class CreateArticleCommand(
    private val articleInfoRepo: ArticleInfoRepo,
    private val articleStatRepo: ArticleStatRepo,
    private val articlePhotoRepo: ArticlePhotoRepo,
): ArticleCommand<Article> {

    override fun execute(obj: Article) {
        //1.创建文章基本信息
        articleInfoRepo.save(obj)
        //2.创建文章统计信息
        articleStatRepo.save(obj)
        //3.创建文章图片信息
        articlePhotoRepo.save(obj)
    }

}

//具体命令
//@Component 将来可以通过构造方法注入
class DeleteArticleCommand(
    private val articleInfoRepo: ArticleInfoRepo,
    private val articleStatRepo: ArticleStatRepo,
    private val articlePhotoRepo: ArticlePhotoRepo,
): ArticleCommand<Long> {

    override fun execute(obj: Long) {
        //1.删除文章基本信息
        articleInfoRepo.del(obj)
        //2.删除文章统计信息
        articleStatRepo.del(obj)
        //3.删除文章图片信息
        articlePhotoRepo.del(obj)
    }

}

//命令调用者
//@Component
class ArticleCommandInvoker {

    fun <T> execute(cmd: ArticleCommand<T>, obj: T) {
        cmd.execute(obj)
    }

}

//@Service
class ArticleService(
    private val articleCommandInvoker: ArticleCommandInvoker,
    private val createArticleCommand: CreateArticleCommand,
) {

    fun pub(article: Article) {
        articleCommandInvoker.execute(createArticleCommand, article)
    }

}


