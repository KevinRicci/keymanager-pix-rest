package br.com.zupacademy.chavePix.consulta

import br.com.zupacademy.ConsultaChavePixRequest
import br.com.zupacademy.KeyManagerPixServiceConsultaGrpc
import br.com.zupacademy.validators.ValidUUID
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.validation.Validated
import jakarta.inject.Inject

@Validated
@Controller("/api/v1")
class ConsultaChavePixController (
    @Inject val clientPix: KeyManagerPixServiceConsultaGrpc.KeyManagerPixServiceConsultaBlockingStub
){

    @Get("/clientes/{idCliente}/chaves/{idChave}")
    fun consultaChavePix(@PathVariable @ValidUUID idCliente: String,
                            @PathVariable @ValidUUID idChave: String): HttpResponse<ConsultaChavePixRestResponse> {

        val response = clientPix.consultaChavePix(ConsultaChavePixRequest
            .newBuilder()
            .setPorPixIdEIdCliente(
                ConsultaChavePixRequest.PorPixIdEIdCliente.newBuilder().
                setUuidCliente(idCliente).
                setPixId(idChave).
                build()).
            build()).toModel()

        return HttpResponse.ok(response)
    }
}