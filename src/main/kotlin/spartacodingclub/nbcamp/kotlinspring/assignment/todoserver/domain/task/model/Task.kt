package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.model.Comment
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskFullResponse
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "task")
class Task(
    @Column(name = "title", nullable = false)
    @field:Size(min = 1, max = 200)
    var title: String,

    @Column(name = "description", nullable = false)
    @field:Size(min = 1, max = 1000)
    var description: String,

    @Column(name = "owner", nullable = false)
    var owner: String
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

    @Column(name = "is_done", nullable = false)
    var isDone: Boolean = false


    @OneToMany(mappedBy = "task", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()


    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }

    fun toResponse(): TaskResponse = TaskResponse(id!!, title, description, isDone, owner, timeCreated!!, timeUpdated!!)
    fun toFullResponse(): TaskFullResponse = TaskFullResponse(id!!, title, description, isDone, owner, timeCreated!!, timeUpdated!!, comments.map { it.toSimplifiedResponse() })
}