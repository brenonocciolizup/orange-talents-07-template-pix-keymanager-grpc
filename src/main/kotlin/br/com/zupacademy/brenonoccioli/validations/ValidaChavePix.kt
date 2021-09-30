package br.com.zupacademy.brenonoccioli.validations

import br.com.zupacademy.brenonoccioli.model.TipoChave
import br.com.zupacademy.brenonoccioli.endpointGrpc.registra.NovaChavePix
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import jakarta.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@MustBeDocumented
@Target(CLASS, TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidaChavePixValidator::class])
annotation class ValidaChavePix(
    val message: String = "Chave com formato inválido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
class ValidaChavePixValidator: ConstraintValidator<ValidaChavePix, NovaChavePix>{
    override fun isValid(
        value: NovaChavePix?,
        annotationMetadata: AnnotationValue<ValidaChavePix>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value?.tipoChave == null) {
            return false
        } else {
            when (value.tipoChave) {
                TipoChave.CPF -> return value.chave.matches("^[0-9]{11}\$".toRegex())

                TipoChave.CELULAR -> return value.chave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())

                TipoChave.EMAIL -> return value.chave.matches("[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex())

                TipoChave.CHAVE_ALEATORIA -> return true
            }
        }
    }
}
