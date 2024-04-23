package org.cyk.kt.solution.design.decorator


//抽象装饰角色
abstract class BlogShowDecorator(
    protected val blogShow: BlogShow
): BlogShow {

    override fun getTitle() = blogShow.getTitle()
    override fun getContent() = blogShow.getContent()
    override fun display() = blogShow.display()

}

//具体装饰角色
class FollowDecorator(
    blogShow: BlogShow
): BlogShowDecorator(blogShow) {

    override fun display() {
        val title = super.getTitle()
        val content = if(super.getContent().length > 60)
            super.getContent().substring(0, 60).plus("...")
        else
            super.getContent()

        println("title: $title")
        println("content: $content")
        println("> 关注作者，查看更多内容！")
    }

}

//具体装饰角色
class VipDecorator(
    blogShow: BlogShow
): BlogShowDecorator(blogShow) {

    override fun display() {
        val title = super.getTitle()
        val content = if(super.getContent().length > 60)
           super.getContent().substring(0, 60).plus("...")
        else
           super.getContent()

        println("title: $title")
        println("content: $content")
        println("> 充值 vip，查看更多内容！")
    }

}

fun main() {
    //假设从数据库中拿到数据
    val dbBlog = BaseBlogShow("Kotlin (programming language)", "Kotlin is a cross-platform, statically typed, general-purpose high-level programming language with type inference. Kotlin is designed to interoperate fully")
    //这里根据用户的权限来给文章进行不同的内容
    //...
    //假设用户未关注对方，但是对方的文章设置了关注才能访问
    //那么此处添加 "关注装饰器"
    var blog = FollowDecorator(dbBlog)
    //最后给该用户展示
    blog.display()
}
