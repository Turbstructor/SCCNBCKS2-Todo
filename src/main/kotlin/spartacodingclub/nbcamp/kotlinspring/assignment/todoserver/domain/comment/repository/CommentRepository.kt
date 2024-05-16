package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.model.Comment

interface CommentRepository: JpaRepository<Comment, Long>{
    fun findByTaskIdAndId(taskId: Long, id: Long): Comment?
}