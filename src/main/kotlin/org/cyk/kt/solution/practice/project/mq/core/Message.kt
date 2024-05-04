package org.cyk.kt.solution.practice.project.mq.core

import java.util.UUID

data class Message(
    val messageId: String,
    val content: Any,
) {

    companion object {
        fun createMessage(content: Any) = Message(
            messageId = "M-${UUID.randomUUID()}",
            content = content,
        )
    }

}