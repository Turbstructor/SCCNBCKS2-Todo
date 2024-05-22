package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskFullResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.task.service.TaskService

@RequestMapping("/tasks")
@RestController
class TaskController(
    private val taskService: TaskService
) {

    @PostMapping
    fun createTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.createTask(request))


    @GetMapping
    fun getAllTasks(@RequestParam(required = false) author: String?, @RequestParam(required = false) sortByTimeCreatedAsc: Boolean?): ResponseEntity<List<TaskResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getAllTasks(author, sortByTimeCreatedAsc))

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: Long): ResponseEntity<TaskFullResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getTask(taskId))


    @PutMapping("/{taskId}")
    fun updateTask(@PathVariable taskId: Long, @RequestBody request: UpdateTaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.updateTask(taskId, request))


    @PatchMapping("/{taskId}/completion")
    fun toggleTaskCompletion(@PathVariable taskId: Long): ResponseEntity<Unit> {
        taskService.toggleTaskCompletion(taskId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


    @DeleteMapping("/{taskId}")
    fun removeTask(@PathVariable taskId: Long): ResponseEntity<Unit> {
        taskService.removeTask(taskId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}