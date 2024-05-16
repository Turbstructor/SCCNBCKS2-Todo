package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response

import java.time.LocalDateTime

data class CommentSimplifiedResponse(
    val id: Long,
    val content: String,
    val owner: String,
    val timeCreated: LocalDateTime,
    val timeUpdated: LocalDateTime
)