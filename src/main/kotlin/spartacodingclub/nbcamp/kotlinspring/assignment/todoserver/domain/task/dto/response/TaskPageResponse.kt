package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response

data class TaskPageResponse(
    val content: List<TaskFullResponse>,
    val pageNumber: Int,
    val isLast: Boolean
)
