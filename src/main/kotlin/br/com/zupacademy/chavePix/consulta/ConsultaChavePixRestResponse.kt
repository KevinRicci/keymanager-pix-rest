package br.com.zupacademy.chavePix.consulta

import br.com.zupacademy.chavePix.TipoChave
import br.com.zupacademy.chavePix.TipoConta
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
open class ConsultaChavePixRestResponse(
    val pixId: String?,
    val idCliente: String?,
    val tipoChave: TipoChave,
    val valorChave: String,
    val nomeTitular: String,
    val cpfTitular: String,
    val nomeInstituicao: String,
    val agencia: Int,
    val numeroConta: Int,
    val tipoConta: TipoConta,
    val horaCadastro: LocalDateTime
) {
}