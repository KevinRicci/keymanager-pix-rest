package br.com.zupacademy.chavePix.consulta

import br.com.zupacademy.ConsultaChavePixResponse
import br.com.zupacademy.chavePix.TipoChave
import br.com.zupacademy.chavePix.TipoConta
import java.time.LocalDateTime
import java.time.ZoneOffset

fun ConsultaChavePixResponse.toModel(): ConsultaChavePixRestResponse{
    return ConsultaChavePixRestResponse(
        pixId = pixId ?: "",
        idCliente = uuidCliente ?: "",
        tipoChave = TipoChave.valueOf(tipoChave.name),
        valorChave = valorChave,
        nomeTitular = nomeTitular,
        cpfTitular = cpfTitular,
        nomeInstituicao = nomeInstituicao,
        agencia = agencia,
        numeroConta = numeroConta,
        tipoConta = TipoConta.valueOf(tipoConta.name),
        horaCadastro = LocalDateTime.ofEpochSecond(horaCadastro.seconds, horaCadastro.nanos, ZoneOffset.UTC)
    )
}