package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model.Task
import java.util.*

interface TaskRepository: JpaRepository<Task, Long> {
    @EntityGraph(attributePaths = ["comments"])
    fun findAllByOwner(owner: String): List<Task>

    @EntityGraph(attributePaths = ["comments"])
    override fun findAll(): List<Task>
}