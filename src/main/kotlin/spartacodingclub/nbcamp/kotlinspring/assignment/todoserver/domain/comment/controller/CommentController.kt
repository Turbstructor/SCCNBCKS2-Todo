package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.CreateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.RemoveCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.request.UpdateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.comment.dto.response.CommentResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.service.TaskService

@RequestMapping("/api/tasks/{taskId}/comments")
@RestController
class CommentController(
    private val taskService: TaskService
) {

    @PostMapping
    fun createComment(
        @PathVariable taskId: Long,
        @RequestBody request: CreateCommentRequest
    ): ResponseEntity<CommentResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.createComment(taskId, request))


    @GetMapping
    fun getCommentsList(@PathVariable taskId: Long): ResponseEntity<List<CommentResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getCommentsList(taskId))

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable taskId: Long, @PathVariable commentId: Long): ResponseEntity<CommentResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getComment(taskId, commentId))


    @PatchMapping("/{commentId}")
    fun updateComment(
        @PathVariable taskId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.updateComment(taskId, commentId, request))


    @DeleteMapping("/{commentId}")
    fun removeComment(
        @PathVariable taskId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: RemoveCommentRequest
    ): ResponseEntity<Unit> {
        taskService.removeComment(taskId, commentId, request)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}