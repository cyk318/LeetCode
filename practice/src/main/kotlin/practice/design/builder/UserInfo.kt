package org.cyk.solution.design.builder

data class UserInfo private constructor(
    val id: Long?,
    val username: String,
    val password: String,
    val age: Int?,
) {

    private constructor(builder: Builder): this(builder.id, builder.username, builder.password, builder.age)

    object Builder {

        var id: Long? = null
        lateinit var username: String
        lateinit var password: String
        var age: Int? = null

        fun id(id: Long) = apply { this.id = id }
        fun username(username: String) = apply { this.username = username }
        fun password(password: String) = apply { this.password = password }
        fun age(age: Int) = apply { this.age = age }
        fun build() = UserInfo(this)

    }

}

fun main() {
    val user = UserInfo.Builder
        .id(1)
        .username("cyk")
        .password("1111")
        .age(20)
        .build()
}
