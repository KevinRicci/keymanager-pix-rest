package br.com.zupacademy.chavePix.consulta

import br.com.zupacademy.chavePix.TipoChave
import br.com.zupacademy.chavePix.TipoConta
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
class BuscaChavesPixRestResponse (
    val pixId: String,
    val idCliente: String,
    val tipoChave: TipoChave,
    val valorChave: String,
    val tipoConta: TipoConta,
    val horaCadastro: LocalDateTime
){
}