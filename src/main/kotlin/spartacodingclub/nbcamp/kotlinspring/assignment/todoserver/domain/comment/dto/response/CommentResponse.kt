package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response

import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskResponse
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val owner: String,
    val timeCreated: LocalDateTime,
    val timeUpdated: LocalDateTime,
    val taskRelated: TaskResponse
)