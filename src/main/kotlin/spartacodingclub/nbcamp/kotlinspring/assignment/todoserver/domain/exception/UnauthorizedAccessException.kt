package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception

data class UnauthorizedAccessException(val target: String):
        RuntimeException("Unauthorized access to item ${target}")
