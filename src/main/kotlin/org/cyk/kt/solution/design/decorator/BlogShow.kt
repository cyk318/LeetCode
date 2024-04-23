package org.cyk.kt.solution.design.decorator


//抽象构建角色
interface BlogShow {

    fun getTitle(): String
    fun getContent(): String
    fun display()

}

//具体构建角色
data class BaseBlogShow(
    private val title: String,
    private val content: String
): BlogShow {

    override fun getTitle() = title

    override fun getContent() = content

    override fun display() {
        println("title: $title")
        println("content: $content")
    }

}
