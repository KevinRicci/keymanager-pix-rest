package br.com.zupacademy.chavePix.deleta

import br.com.zupacademy.DeletaChaveRequest
import br.com.zupacademy.KeyManagerPixServiceDeletaGrpc
import br.com.zupacademy.validators.ValidUUID
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.validation.Validated
import jakarta.inject.Inject

@Validated
@Controller("/api/v1")
class DeletaChavePixController(
    @Inject val clientePix: KeyManagerPixServiceDeletaGrpc.KeyManagerPixServiceDeletaBlockingStub
){

    @Delete("/clientes/{idCliente}/chaves/{idChave}")
    fun deletaChave(@PathVariable @ValidUUID idCliente: String, @PathVariable @ValidUUID idChave: String): HttpResponse<Any>{
        val response = clientePix.deletaChave(DeletaChaveRequest.newBuilder()
            .setUuidCliente(idCliente)
            .setPixId(idChave)
            .build())

        return HttpResponse.ok(response.pixId)
    }
}