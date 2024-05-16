package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request

data class UpdateCommentRequest(
    val content: String,
    val owner: String,
    val password: String
)
