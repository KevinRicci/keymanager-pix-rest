package br.com.zupacademy.validators

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@ReportAsSingleViolation
@Constraint(validatedBy = [])
@Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
@Retention(AnnotationRetention.RUNTIME)
@Target(FIELD, VALUE_PARAMETER, CONSTRUCTOR, PROPERTY)
annotation class ValidUUID(
    val message: String = "UUID com formato inválido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
