package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
//        ResponseEntity
//            .status(HttpStatus.BAD_REQUEST)
//            .body(ErrorResponse("MethodArgumentNotValidException: ${ex.bindingResult.fieldErrors.map { "[Invalid ${it.field}: ${it.defaultMessage}]" }.joinToString(" / ")}"))

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("ConstraintViolationException: ${ex.constraintViolations.map { "Invalid ${it.propertyPath}: ${it.message}]" }.joinToString(" / ")}"))
}