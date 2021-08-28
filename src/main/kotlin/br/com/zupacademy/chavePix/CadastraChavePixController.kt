package br.com.zupacademy.chavePix

import br.com.zupacademy.ChavePixRequest
import br.com.zupacademy.KeyManagerPixServiceCadastraGrpc
import br.com.zupacademy.TipoChave
import br.com.zupacademy.TipoConta
import br.com.zupacademy.validators.ValidUUID
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import java.net.URI
import javax.validation.Valid

@Validated
@Controller("/api/v1")
class CadastraChavePixController(
    @Inject val clientePix: KeyManagerPixServiceCadastraGrpc.KeyManagerPixServiceCadastraBlockingStub
) {

    @Post("/clientes/{uuidCliente}/chaves")
    fun cadastraChavePix(@PathVariable @ValidUUID uuidCliente: String,
                         @Valid @Body cadastraChavePixRequest: CadastraChavePixRequest): HttpResponse<Any>{

        val response = clientePix.cadastraChave(
            ChavePixRequest.newBuilder()
                .setUuidCliente(uuidCliente)
                .setTipoChave(TipoChave.valueOf(cadastraChavePixRequest.tipoChave.name))
                .setValorChave(cadastraChavePixRequest?.valorChave ?: "")
                .setTipoConta(TipoConta.valueOf(cadastraChavePixRequest.tipoConta.name))
                .build())

        val location = URI.create("/api/v1/clientes/${uuidCliente}/chaves/${response.pixId}")

        return HttpResponse.created(
            response.pixId,
            location
        )
    }
}