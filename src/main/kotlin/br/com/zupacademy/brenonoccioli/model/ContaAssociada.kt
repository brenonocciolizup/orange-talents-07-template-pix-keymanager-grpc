package br.com.zupacademy.brenonoccioli.model

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank

@Embeddable
class ContaAssociada(
    @field:NotBlank
    @Column(nullable = false)
    val instituicaoNome: String,

    @field:NotBlank
    @Column(nullable = false)
    val instituicaoIspb: String?,

    @field:NotBlank
    @Column(nullable = false)
    val nomeDoTitular: String,

    @field:NotBlank
    @Column(nullable = false)
    val documentoDoTitular: String,

    @field:NotBlank
    @Column(nullable = false)
    val agencia: String,

    @field:NotBlank
    @Column(nullable = false)
    val numeroDaConta: String
)