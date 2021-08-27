package br.com.zupacademy.exception

import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.badRequest
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
class GlobalExceptionHandler : ExceptionHandler<StatusRuntimeException, HttpResponse<DetalhesErro>> {
    override fun handle(request: HttpRequest<*>, exception: StatusRuntimeException): HttpResponse<DetalhesErro> {
        return exception.toHttpResponse()
    }
}