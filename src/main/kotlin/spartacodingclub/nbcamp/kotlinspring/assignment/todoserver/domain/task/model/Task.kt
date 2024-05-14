package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskResponse
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "task")
class Task(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
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


    fun toResponse(): TaskResponse = TaskResponse(id, title, description, owner, timeCreated, timeUpdated)
}