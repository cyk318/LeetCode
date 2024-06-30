package practice.design.demo1.facade

class UserinfoRepoImpl: UserinfoRepo {
    override fun save() = println("save userinfo ...")
    override fun query() = println("query userinfo ...")
}
class UserStatRepoImpl: UserStatRepo {
    override fun save() = println("save user_stat ...")
    override fun query() = println("query user_stat ...")
}
class UserAvatarRepoImpl: UserAvatarRepo {
    override fun save() = println("save user_avatar ...")
    override fun query() = println("query user_avatar ...")
}

interface UserinfoRepo {
    fun save()
    fun query()
}
interface UserStatRepo {
    fun save()
    fun query()
}
interface UserAvatarRepo {
    fun save()
    fun query()
}

class UserFacadeImpl(
    private val infoRepo: UserinfoRepo,
    private val statRepo: UserStatRepo,
    private val avatarRepo: UserAvatarRepo,
): UserFacade {

    override fun save() {
        infoRepo.save()
        statRepo.save()
        avatarRepo.save()
    }
    override fun query() {
        infoRepo.query()
        statRepo.query()
        avatarRepo.query()
    }
}
interface UserFacade {
    fun save()
    fun query()
}

fun main() {
    val userFacade: UserFacade = UserFacadeImpl(
        infoRepo = UserinfoRepoImpl(),
        statRepo = UserStatRepoImpl(),
        avatarRepo = UserAvatarRepoImpl(),
    )

    userFacade.save()
    println("------------------------------")
    userFacade.query()
}






