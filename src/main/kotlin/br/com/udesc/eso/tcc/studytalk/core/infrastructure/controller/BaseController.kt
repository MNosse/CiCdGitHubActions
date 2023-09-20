package br.com.udesc.eso.tcc.studytalk.core.infrastructure.controller

import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.function.Consumer

abstract class BaseController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(e: MethodArgumentNotValidException): Map<String, String> {
        val errors: MutableMap<String, String> = HashMap()
        e.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val field = (error as FieldError).field
            val message = error.getDefaultMessage()
            errors[field] = message ?: ""
        })
        return errors
    }
}