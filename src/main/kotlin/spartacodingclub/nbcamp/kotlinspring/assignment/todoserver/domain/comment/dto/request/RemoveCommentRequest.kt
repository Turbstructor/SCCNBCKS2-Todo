package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request

data class RemoveCommentRequest(
    val owner: String,
    val password: String
)
