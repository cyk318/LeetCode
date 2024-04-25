package org.cyk.kt.solution.practice.design.command

import org.cyk.kt.solution.practice.design.strategy.Article

class ArticleInfoRepo {
    fun save(article: Article) {
        println("save ArticleInfo")
    }
    fun del(id: Long) {}
}
class ArticleStatRepo {
    fun save(article: Article) {
        println("save ArticleStat")
    }
    fun del(id: Long) {}
}
class ArticlePhotoRepo {
    fun save(article: Article) {
        println("save ArticlePhoto")
    }
    fun del(id: Long) {}
}
