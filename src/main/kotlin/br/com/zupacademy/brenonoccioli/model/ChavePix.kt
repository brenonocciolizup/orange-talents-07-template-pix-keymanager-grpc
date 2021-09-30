package br.com.zupacademy.brenonoccioli.model

import br.com.zupacademy.brenonoccioli.validations.ValidaUUID
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class ChavePix(
    @field:ValidaUUID
    @field:NotBlank
    @Column(nullable = false)
    val idCliente: String,

    @field:NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoChave: TipoChave?,

    @field:NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoConta: TipoDeConta?,

    @field:NotBlank @field:Size(max = 77)
    @Column(nullable = false, unique = true)
    var chave: String,

    @field:Valid
    @Embedded
    val conta: ContaAssociada,
) {

    @Id
    @GeneratedValue
    val id: Long? = null

    @Column(nullable = false, unique = true)
    val idPix: String = UUID.randomUUID().toString()

    @Column(nullable = false)
    val criadaEm: LocalDateTime = LocalDateTime.now()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChavePix

        if (idCliente != other.idCliente) return false
        if (tipoChave != other.tipoChave) return false
        if (tipoConta != other.tipoConta) return false
        if (chave != other.chave) return false
        if (idPix != other.idPix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idCliente.hashCode()
        result = 31 * result + (tipoChave?.hashCode() ?: 0)
        result = 31 * result + (tipoConta?.hashCode() ?: 0)
        result = 31 * result + chave.hashCode()
        result = 31 * result + idPix.hashCode()
        return result
    }


}
