package br.com.zupacademy.exception

import io.grpc.StatusRuntimeException
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
@Replaces(io.micronaut.validation.exceptions.ConstraintExceptionHandler::class)
class ConstraintViolationExceptionHandler : ExceptionHandler<ConstraintViolationException, HttpResponse<DetalhesErro>> {
    override fun handle(request: HttpRequest<*>, exception: ConstraintViolationException): HttpResponse<DetalhesErro> {
        val violations = exception.constraintViolations
        return HttpResponse.badRequest(DetalhesErro(
            "Erro de validação",
            violations.map {
                Erro(null, it.message)
            }
        ))
    }
}