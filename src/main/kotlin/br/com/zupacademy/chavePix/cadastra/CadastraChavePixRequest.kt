package br.com.zupacademy.chavePix.cadastra

import br.com.zupacademy.chavePix.TipoChave
import br.com.zupacademy.chavePix.TipoConta
import br.com.zupacademy.validators.ValidChavePix
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotNull

@ValidChavePix
@Introspected
data class CadastraChavePixRequest(
    @field:NotNull
    val tipoChave: TipoChave,
    val valorChave: String?,
    @field:NotNull
    val tipoConta: TipoConta
) {
}