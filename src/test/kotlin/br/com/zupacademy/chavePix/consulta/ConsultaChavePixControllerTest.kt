package br.com.zupacademy.chavePix.consulta

import br.com.zupacademy.*
import br.com.zupacademy.client.ClientFactoryGrpc
import com.google.protobuf.Timestamp
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
internal class ConsultaChavePixControllerTest(
    @Inject val clientPix: KeyManagerPixServiceConsultaGrpc.KeyManagerPixServiceConsultaBlockingStub
){
    @field:Inject
    @field:Client("/")
    lateinit var httpClient: HttpClient

    @Test
    fun `deve consultar os dados de uma chave pix`(){
        //cenário
        val idCLiente = UUID.randomUUID().toString()
        val idChave = UUID.randomUUID().toString()

        Mockito.`when`(clientPix.consultaChavePix(Mockito.any())).thenReturn(
            ConsultaChavePixResponse.newBuilder()
                .setPixId(idChave)
                .setUuidCliente(idCLiente)
                .setTipoChave(TipoChave.EMAIL)
                .setValorChave("kevin@gmail.com")
                .setNomeTitular("Kevin")
                .setCpfTitular("52037557425")
                .setNomeInstituicao("Itaú Unibanco S.A")
                .setAgencia("1234")
                .setNumeroConta("1234")
                .setTipoConta(TipoConta.CONTA_CORRENTE)
                .setHoraCadastro(Timestamp.getDefaultInstance())
                .build()
        )

        //ação
        val request = HttpRequest.GET<String>("/api/v1/clientes/$idCLiente/chaves/$idChave")
        val resposta = httpClient.toBlocking().exchange(request, ConsultaChavePixRestResponse::class.java)

        //validação
        assertEquals(HttpStatus.OK, resposta.status)
        assertEquals(idCLiente, resposta.body().idCliente)
        assertEquals(idChave, resposta.body().pixId)
    }
}

@Factory
@Replaces(factory = ClientFactoryGrpc::class)
class CLientMock{

    @Singleton
    fun mockStub() = Mockito.mock(KeyManagerPixServiceConsultaGrpc.KeyManagerPixServiceConsultaBlockingStub::class.java)
}