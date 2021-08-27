package br.com.zupacademy.exception

import io.grpc.Status.Code.*
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError

fun StatusRuntimeException.toHttpResponse(): HttpResponse<DetalhesErro>{
    val descricao = status.description ?: ""
    return when(status.code){
        INVALID_ARGUMENT -> HttpResponse.badRequest(DetalhesErro(descricao, emptyList()))
        ALREADY_EXISTS -> HttpResponse.status<JsonError>(HttpStatus.UNPROCESSABLE_ENTITY).body(DetalhesErro(descricao, emptyList()))
        NOT_FOUND -> HttpResponse.notFound(DetalhesErro(descricao, emptyList()))
        PERMISSION_DENIED -> HttpResponse.status<JsonError>(HttpStatus.FORBIDDEN).body(DetalhesErro(descricao, emptyList()))
        INTERNAL -> HttpResponse.status<JsonError>(HttpStatus.INTERNAL_SERVER_ERROR).body(DetalhesErro(descricao, emptyList()))
        else -> HttpResponse.status<JsonError>(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(DetalhesErro("Não foi possível completar a requisição devido ao erro: ${status.code} $descricao",
            emptyList()))
    }
}