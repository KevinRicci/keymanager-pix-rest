package br.com.zupacademy.validators

import br.com.zupacademy.chavePix.cadastra.CadastraChavePixRequest
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Constraint(validatedBy = [ChavePixValidator::class])
annotation class ValidChavePix(
    val message: String = "valor da chave inválido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class ChavePixValidator(): ConstraintValidator<ValidChavePix, CadastraChavePixRequest>{
    override fun isValid(
        value: CadastraChavePixRequest,
        annotationMetadata: AnnotationValue<ValidChavePix>,
        context: io.micronaut.validation.validator.constraints.ConstraintValidatorContext
    ): Boolean {

        return value.tipoChave.validaValor(value?.valorChave)
    }
}