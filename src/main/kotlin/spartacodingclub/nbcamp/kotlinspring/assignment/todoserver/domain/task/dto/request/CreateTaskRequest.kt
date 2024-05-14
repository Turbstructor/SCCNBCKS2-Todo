package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request

data class CreateTaskRequest (
    val title: String,
    val description: String,
    val owner: String
)