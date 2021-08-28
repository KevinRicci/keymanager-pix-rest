package br.com.zupacademy.chavePix

import br.com.zupacademy.ChavePixResponse
import br.com.zupacademy.KeyManagerPixServiceCadastraGrpc
import br.com.zupacademy.client.ClientFactoryGrpc
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

@MicronautTest
internal class CadastraChavePixControllerTest(
) {

    @field:Inject
    lateinit var clientPix: KeyManagerPixServiceCadastraGrpc.KeyManagerPixServiceCadastraBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    @Test
    fun `deve cadastrar uma nova chave pix`() {
        //cenário
        val uuidCliente = UUID.randomUUID().toString()
        Mockito.`when`(clientPix.cadastraChave(Mockito.any())).thenReturn(
            ChavePixResponse.newBuilder().setPixId("9dbff17a-c67a-4d50-b5d1-3d587c8f8592").build()
        )

        //ação
        val request = HttpRequest.POST<CadastraChavePixRequest>("/api/v1/clientes/$uuidCliente/chaves", CadastraChavePixRequest(
            br.com.zupacademy.chavePix.TipoChave.EMAIL,
            "teste@test.com",
            br.com.zupacademy.chavePix.TipoConta.CONTA_POUPANCA
        ))
        val response = httpClient.toBlocking().exchange(request, String::class.java)

        //validação
        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location").contains("9dbff17a-c67a-4d50-b5d1-3d587c8f8592"))
    }
}
@Factory
@Replaces(factory = ClientFactoryGrpc::class)
internal class MockitoStubFactory{

    @Singleton
    fun stubMock() = Mockito.mock(KeyManagerPixServiceCadastraGrpc.KeyManagerPixServiceCadastraBlockingStub::class.java)
}

