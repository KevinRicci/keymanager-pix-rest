package br.com.zupacademy.exception

import io.grpc.Status
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GrpcExceptionHandlerTest{

    val request = HttpRequest.GET<Any>("/")  //simulando uma requisição

    @Test
    fun `deve retornar BAD REQUEST quando grpc retornar INVALID_ARGUMENT`(){
        //cenário
        val exception = Status.INVALID_ARGUMENT.withDescription("Argumento inválido").asRuntimeException()
        //ação
        val resposta = GrpcExceptionHandler().handle(request, exception)
        //validação
        assertEquals(HttpStatus.BAD_REQUEST, resposta.status)
        assertEquals("Argumento inválido", resposta.body().mensagem)
    }

    @Test
    fun `deve retornar UNPROCESSABLE_ENTTIY quando grpc retornar ALREADY_EXISTS`(){
        //cenário
        val exception = Status.ALREADY_EXISTS.withDescription("Chave já existente").asRuntimeException()
        //ação
        val resposta = GrpcExceptionHandler().handle(request, exception)
        //validação
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resposta.status)
        assertEquals("Chave já existente", resposta.body().mensagem)
    }

    @Test
    fun `deve retornar NOT FOUND quando grpc retornar NOT FOUND`(){
        //cenário
        val exception = Status.NOT_FOUND.withDescription("Chave não encontrada").asRuntimeException()
        //ação
        val resposta = GrpcExceptionHandler().handle(request, exception)
        //validação
        assertEquals(HttpStatus.NOT_FOUND, resposta.status)
        assertEquals("Chave não encontrada", resposta.body().mensagem)
    }

    @Test
    fun `deve retornar FORBIDDEN quando grpc retornar PERMISSION_DENIED`(){
        //cenário
        val exception = Status.PERMISSION_DENIED.withDescription("Sem permissão para acessar o recurso").asRuntimeException()
        //ação
        val resposta = GrpcExceptionHandler().handle(request, exception)
        //validação
        assertEquals(HttpStatus.FORBIDDEN, resposta.status)
        assertEquals("Sem permissão para acessar o recurso", resposta.body().mensagem)
    }

    @Test
    fun `deve retornar BAD_GATEWAY quando grpc retornar INTERNAL`(){
        //cenário
        val exception = Status.INTERNAL.withDescription("Erro ao processar requisição").asRuntimeException()
        //ação
        val resposta = GrpcExceptionHandler().handle(request, exception)
        //validação
        assertEquals(HttpStatus.BAD_GATEWAY, resposta.status)
        assertEquals("Erro ao processar requisição", resposta.body().mensagem)
    }

    @Test
    fun `deve retornar BAD_GATEWAY quando grpc retornar outro erro inesperado`(){
        //cenário
        val exception = Status.UNKNOWN.withDescription("erro desconhecido").asRuntimeException()
        //ação
        val resposta = GrpcExceptionHandler().handle(request, exception)
        //validação
        assertEquals(HttpStatus.BAD_GATEWAY, resposta.status)
        assertEquals("Não foi possível completar a requisição devido ao erro: erro desconhecido", resposta.body().mensagem)
    }
}