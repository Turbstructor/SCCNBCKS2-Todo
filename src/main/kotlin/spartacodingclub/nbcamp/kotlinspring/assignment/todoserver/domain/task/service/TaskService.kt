package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception.ItemNotFoundException
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.repository.TaskRepository

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {

    @Transactional
    fun createTask(request: CreateTaskRequest): TaskResponse {
        val task = Task(request.title, request.description, request.owner)
        return taskRepository.save(task).toResponse()
    }

    fun getAllTasks(): List<TaskResponse> = taskRepository.findAll().map { it.toResponse() }

    fun getTask(taskId: Long): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        return task.toResponse()
    }

    @Transactional
    fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")

        var isModified =
            ((task.title != request.title) or (task.description != request.description) or (task.owner != request.owner))
        if (isModified) {
            task.title = request.title
            task.description = request.description
            task.owner = request.owner
        }

        return if (isModified) taskRepository.save(task).toResponse() else task.toResponse()
    }

    @Transactional
    fun removeTask(taskId: Long) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        taskRepository.delete(task)
    }
}