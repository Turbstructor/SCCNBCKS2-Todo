package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.CreateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.UpdateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response.CommentResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response.CommentSimplifiedResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception.UnauthorizedAccessException
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model.Task
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "comment")
class Comment(
    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "owner", nullable = false)
    var owner: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    var task: Task
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    @Column(name = "time_created")
    @CreatedDate
    var timeCreated: LocalDateTime? = null

    @Column(name = "time_updated")
    @LastModifiedDate
    var timeUpdated: LocalDateTime? = null


    constructor (request: CreateCommentRequest, relatedTask: Task) : this(
        content = request.content,
        owner = request.owner,
        password = request.password,
        task = relatedTask
    )

    fun update(request: UpdateCommentRequest) {
        if (request.owner != this.owner || request.password != this.password) throw UnauthorizedAccessException("comment")

        this.content = request.content
    }

    fun toResponse(): CommentResponse =
        CommentResponse(id!!, content, owner, timeCreated!!, timeUpdated!!, task.toResponse())

    fun toSimplifiedResponse(): CommentSimplifiedResponse =
        CommentSimplifiedResponse(id!!, content, owner, timeCreated!!, timeUpdated!!)
}