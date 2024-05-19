package practice.design.demo4


data class User (
    val id: Long,
    val username: String,
    val password: String,
    val age: Int?,
) {

    object Builder {
        private var id: Long? = null
        private var username: String? = null
        private var password: String? = null
        private var age: Int? = null

        fun id(id: Long) = this.apply { this.id = id }
        fun username(username: String) = this.apply { this.username = username }
        fun password(password: String) = this.apply { this.password = password }
        fun age(age: Int) = this.apply { this.age = age }

        fun build(): User {
            requireNotNull(id) { "id is required" }
            require(!username.isNullOrBlank()) { " username is required" }
            require(!password.isNullOrBlank()) { " password is required" }
            return User(id!!, username!!, password!!, age)
        }

    }

}

fun main() {
    val b = User.Builder
        .id(1)
        .username("cyk")
        .password("1111")
        .age(19)
        .build()
    println(b)
}


