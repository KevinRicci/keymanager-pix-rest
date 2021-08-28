package br.com.zupacademy.chavePix.deleta

import br.com.zupacademy.DeletaChaveRequest
import br.com.zupacademy.DeletaChaveResponse
import br.com.zupacademy.KeyManagerPixServiceDeletaGrpc
import br.com.zupacademy.client.ClientFactoryGrpc
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

@MicronautTest
internal class DeletaChavePixControllerTest(
    @Inject val clientPix: KeyManagerPixServiceDeletaGrpc.KeyManagerPixServiceDeletaBlockingStub
){

    @field:Inject
    @field:Client("/")
    lateinit var clienteHttp: HttpClient

    @Test
    fun `deve deletar uma chave`(){
        //cenário
        val ID_CLIENTE = UUID.randomUUID().toString()
        val ID_CHAVE = UUID.randomUUID().toString()

        Mockito.`when`(clientPix.deletaChave(DeletaChaveRequest.newBuilder()
            .setUuidCliente(ID_CLIENTE)
            .setPixId(ID_CHAVE)
            .build())).thenReturn(DeletaChaveResponse.newBuilder().setPixId(ID_CHAVE).build())

        //ação
        val request = HttpRequest.DELETE("/api/v1/clientes/$ID_CLIENTE/chaves/$ID_CHAVE", String::class.java)
        val resposta = clienteHttp.toBlocking().exchange(request, String::class.java)

        //validação
        assertEquals(HttpStatus.OK, resposta.status)
        assertEquals(ID_CHAVE, resposta.body().toString())
    }
}

@Factory
@Replaces(factory = ClientFactoryGrpc::class)
class MockitoStubFactory{

    @Singleton
    fun clientStub() = Mockito.mock(KeyManagerPixServiceDeletaGrpc.KeyManagerPixServiceDeletaBlockingStub::class.java)
}