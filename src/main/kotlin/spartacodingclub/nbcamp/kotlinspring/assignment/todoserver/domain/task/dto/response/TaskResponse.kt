package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response

import java.time.LocalDateTime

data class TaskResponse(
    val id: Long?,
    val title: String,
    val description: String,
    val owner: String,
    val timeCreated: LocalDateTime?,
    val timeUpdated: LocalDateTime?
)
