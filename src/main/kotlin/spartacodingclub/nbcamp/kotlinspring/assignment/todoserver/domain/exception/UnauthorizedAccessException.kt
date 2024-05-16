package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception

data class UnauthorizedAccessException(override val message: String?):
        RuntimeException(message ?: "Unauthorized access to item")
