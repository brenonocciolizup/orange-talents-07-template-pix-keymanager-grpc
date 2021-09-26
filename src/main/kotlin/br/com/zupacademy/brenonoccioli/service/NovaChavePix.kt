package br.com.zupacademy.brenonoccioli.service

import br.com.zupacademy.brenonoccioli.model.ChavePix
import br.com.zupacademy.brenonoccioli.model.ContaAssociada
import br.com.zupacademy.brenonoccioli.model.TipoChave
import br.com.zupacademy.brenonoccioli.model.TipoDeConta
import br.com.zupacademy.brenonoccioli.validations.ValidaChavePix
import br.com.zupacademy.brenonoccioli.validations.ValidaUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
@ValidaChavePix
@Introspected
data class NovaChavePix(
    @field:ValidaUUID
    @field:NotBlank
    val clienteId: String,

    @field:NotNull
    val tipoDeConta: TipoDeConta?,

    @field:NotNull
    val tipoChave: TipoChave?,

    @field:Size(max=77)
    val chave: String
) {
    fun toModel(conta: ContaAssociada): ChavePix {
        return ChavePix(
            idCliente = clienteId,
            tipoChave = tipoChave,
            chave = if(this.tipoChave == TipoChave.CHAVE_ALEATORIA) UUID.randomUUID().toString() else this.chave,
            tipoConta = tipoDeConta,
            conta = conta
        )
    }
}
