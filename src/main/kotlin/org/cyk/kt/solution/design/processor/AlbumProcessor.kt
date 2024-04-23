package org.cyk.kt.solution.design.processor

data class Album (
    val title: String,
    val content: String,
    val photos: List<String>
)

abstract class AlbumProcessor {

    //模板方法，定义了专辑处理的算法骨架
    fun processAlbum(album: Album) {
        valid(album) //验证专辑有效性
        logStart(album) //记录开始处理日志
        doProcess(album) //执行具体的专辑处理逻辑
        logEnd(album) //记录结束处理日志
    }


    private fun valid(album: Album) {
        // 校验专辑标题、内容、图片是否合法
        println("Valid album ......")
    }

    private fun logStart(album: Album) {
        //记录专辑处理开始的日志
        println("[album title: ${album.title}] log start....")
    }

    private fun logEnd(album: Album) {
        //记录专辑处理结束的日志
        println("[album title: ${album.title}] log end")
    }

    //抽象方法，由子类实现专辑的具体处理逻辑
    protected abstract fun doProcess(album: Album)

}

//文章创建处理器
class AlbumCreateProcessor: AlbumProcessor() {

    override fun doProcess(album: Album) {
        //保存专辑到数据库
        println("save to db")
    }

}

class AlbumEditProcessor: AlbumProcessor() {

    override fun doProcess(album: Album) {
        //更新专辑到数据库
        println("update to db")
    }

}
