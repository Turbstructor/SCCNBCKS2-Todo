package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response

import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response.CommentSimplifiedResponse
import java.time.LocalDateTime

data class TaskFullResponse(
    val id: Long,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val owner: String,
    val timeCreated: LocalDateTime,
    val timeUpdated: LocalDateTime,
    val comments: List<CommentSimplifiedResponse>
)
