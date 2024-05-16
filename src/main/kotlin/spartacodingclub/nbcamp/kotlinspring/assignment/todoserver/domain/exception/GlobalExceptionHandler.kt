package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception.dto.ErrorResponse

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException::class)
    fun handleItemNotFoundException(ex: ItemNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(ex.message))

    @ExceptionHandler(UnauthorizedAccessException::class)
    fun handleUnauthorizedAccessException(ex: UnauthorizedAccessException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(ex.message))
}