package spartacodingclub.nbcamp.kotlinspring.assignment.todoserver.domain.exception

data class ItemNotFoundException(val id: Long?, val modelName: String):
        RuntimeException("Item #$id not found in model $modelName")
