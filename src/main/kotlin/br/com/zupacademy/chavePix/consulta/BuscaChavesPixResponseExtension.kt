package br.com.zupacademy.chavePix.consulta

import br.com.zupacademy.BuscaChavesPixResponse
import br.com.zupacademy.chavePix.TipoChave
import br.com.zupacademy.chavePix.TipoConta
import java.time.LocalDateTime
import java.time.ZoneOffset

fun BuscaChavesPixResponse.toModel(): List<BuscaChavesPixRestResponse>{
    return chavesList.map {
        BuscaChavesPixRestResponse(
            pixId = it.pixId,
            idCliente = it.uuidCliente,
            tipoChave = TipoChave.valueOf(it.tipoChave.name),
            valorChave = it.valorChave,
            tipoConta = TipoConta.valueOf(it.tipoConta.name),
            horaCadastro = LocalDateTime.ofEpochSecond(it.horaCadastro.seconds, it.horaCadastro.nanos, ZoneOffset.UTC)
        )
    }
}