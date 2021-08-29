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

    val idCLiente = UUID.randomUUID().toString()
    val idChave = UUID.randomUUID().toString()

    @Test
    fun `deve consultar os dados de uma chave pix`(){
        //cenário

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
        val request = HttpRequest.GET<String>("/api/v1/clientes/${this.idCLiente}/chaves/$idChave")
        val resposta = httpClient.toBlocking().exchange(request, ConsultaChavePixRestResponse::class.java)

        //validação
        assertEquals(HttpStatus.OK, resposta.status)
        assertEquals(idCLiente, resposta.body().idCliente)
        assertEquals(idChave, resposta.body().pixId)
    }

    @Test
    fun `deve retornar todas as chaves pix`(){
        //cenário
        Mockito.`when`(clientPix.buscaChaves(Mockito.any())).thenReturn(
            BuscaChavesPixResponse.newBuilder()
                .addAllChaves(carregaListaDeChaves())
                .build()
        )

        //ação
        val request = HttpRequest.GET<Any>("/api/v1/clientes/${this.idCLiente}/chaves")
        val resposta = httpClient.toBlocking().exchange(request, List::class.java)

        //validação
        assertEquals(HttpStatus.OK, resposta.status)
        assertEquals(3, resposta.body().size)
    }

    fun carregaListaDeChaves(): List<BuscaChavesPixResponse.Chaves>{

        return listOf<BuscaChavesPixResponse.Chaves>(
            BuscaChavesPixResponse.Chaves.newBuilder()
                .setPixId(this.idChave)
                .setUuidCliente(this.idCLiente)
                .setTipoChave(TipoChave.CELULAR)
                .setValorChave("+5511998736543")
                .setTipoConta(TipoConta.CONTA_POUPANCA)
                .setHoraCadastro(Timestamp.getDefaultInstance())
                .build(),
            BuscaChavesPixResponse.Chaves.newBuilder()
                .setPixId(this.idChave)
                .setUuidCliente(this.idCLiente)
                .setTipoChave(TipoChave.EMAIL)
                .setValorChave("kevin@gmail.com")
                .setTipoConta(TipoConta.CONTA_CORRENTE)
                .setHoraCadastro(Timestamp.getDefaultInstance())
                .build(),
            BuscaChavesPixResponse.Chaves.newBuilder()
                .setPixId(this.idChave)
                .setUuidCliente(this.idCLiente)
                .setTipoChave(TipoChave.CPF)
                .setValorChave("52037558974")
                .setTipoConta(TipoConta.CONTA_POUPANCA)
                .setHoraCadastro(Timestamp.getDefaultInstance())
                .build()
        )
    }
}

@Factory
@Replaces(factory = ClientFactoryGrpc::class)
class CLientMock{

    @Singleton
    fun mockStub() = Mockito.mock(KeyManagerPixServiceConsultaGrpc.KeyManagerPixServiceConsultaBlockingStub::class.java)
}