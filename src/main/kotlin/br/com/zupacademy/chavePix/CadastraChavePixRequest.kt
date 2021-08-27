package br.com.zupacademy.chavePix

import br.com.zupacademy.validators.ValidChavePix
import br.com.zupacademy.validators.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ValidChavePix
@Introspected
data class CadastraChavePixRequest(
    @field:NotNull
    val tipoChave: TipoChave,
    @field:NotBlank
    val valorChave: String,
    @field:NotNull
    val tipoConta: TipoConta
) {
}