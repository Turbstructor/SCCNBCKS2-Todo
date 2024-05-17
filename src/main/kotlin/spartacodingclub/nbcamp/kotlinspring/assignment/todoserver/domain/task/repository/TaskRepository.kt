package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.repository

import org.springframework.data.jpa.repository.JpaRepository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model.Task

interface TaskRepository: JpaRepository<Task, Long> {
    fun findAllByOwner(owner: String): List<Task>
}