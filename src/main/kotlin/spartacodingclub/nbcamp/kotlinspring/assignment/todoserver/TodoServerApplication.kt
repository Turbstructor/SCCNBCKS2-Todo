package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class TodoServerApplication

fun main(args: Array<String>) {
    runApplication<TodoServerApplication>(*args)
}
