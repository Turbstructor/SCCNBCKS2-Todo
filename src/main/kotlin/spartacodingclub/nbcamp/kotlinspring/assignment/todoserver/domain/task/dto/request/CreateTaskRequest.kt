package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTaskRequest (
    @field:Size(min = 1, max = 200)
    val title: String,

    @field:Size(min = 1, max = 1000)
    val description: String,

    @field:NotBlank
    val owner: String
)