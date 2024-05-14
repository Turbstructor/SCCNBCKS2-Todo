package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request

data class UpdateTaskRequest(
    val title: String,
    val description: String,
    val owner: String
)
