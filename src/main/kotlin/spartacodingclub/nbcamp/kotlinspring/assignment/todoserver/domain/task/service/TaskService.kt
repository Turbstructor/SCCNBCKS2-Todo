package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.CreateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.RemoveCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.UpdateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response.CommentResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.model.Comment
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.repository.CommentRepository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception.ItemNotFoundException
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception.UnauthorizedAccessException
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskFullResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.model.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.repository.TaskRepository

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val commentRepository: CommentRepository
) {

    // Tasks

    fun createTask(request: CreateTaskRequest): TaskResponse =
        taskRepository.save(Task(request)).toResponse()

    fun getAllTasks(author: String?, sortByTimeCreatedAsc: Boolean?): List<TaskResponse> {
        val tasksQueried = when (author) {
            null, "" -> taskRepository.findAll()
            else -> taskRepository.findAllByOwner(author)
        }

        return when (sortByTimeCreatedAsc) {
            null -> tasksQueried
            true -> tasksQueried.sortedWith(compareBy { it.timeCreated })
            else -> tasksQueried.sortedWith(compareByDescending { it.timeCreated })
        }.map { it.toResponse() }
    }

    fun getTask(taskId: Long): TaskFullResponse =
        (taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task"))
        .toFullResponse()


    fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        task.update(request)
        return taskRepository.save(task).toResponse()
    }


    fun toggleTaskCompletion(taskId: Long) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        task.toggleCompletion()
        taskRepository.save(task)
    }


    fun removeTask(taskId: Long) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        taskRepository.delete(task)
    }


    // Comments

    @Transactional
    fun createComment(taskId: Long, request: CreateCommentRequest): CommentResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        val comment = Comment(request,task)

        task.addComment(comment)

        commentRepository.save(comment)
        taskRepository.save(task)

        return comment.toResponse()
    }


    fun getCommentsList(taskId: Long): List<CommentResponse> =
        (taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task"))
            .comments.map() { it.toResponse() }

    fun getComment(taskId: Long, commentId: Long): CommentResponse =
        (commentRepository.findByTaskIdAndId(taskId, commentId) ?: throw ItemNotFoundException(
            commentId,
            "comment"
        )).toResponse()


    fun updateComment(taskId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment =
            commentRepository.findByTaskIdAndId(taskId, commentId) ?: throw ItemNotFoundException(commentId, "comment")
        comment.update(request)
        return commentRepository.save(comment).toResponse()
    }


    @Transactional
    fun removeComment(taskId: Long, commentId: Long, request: RemoveCommentRequest) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ItemNotFoundException(taskId, "task")
        val comment =
            commentRepository.findByTaskIdAndId(taskId, commentId) ?: throw ItemNotFoundException(commentId, "comment")

        if (request.owner != comment.owner || request.password != comment.password) throw UnauthorizedAccessException("comment" +
                "")

        task.removeComment(comment)
        commentRepository.delete(comment)
    }
}