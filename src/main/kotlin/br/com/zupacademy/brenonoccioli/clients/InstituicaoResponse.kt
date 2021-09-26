package br.com.zupacademy.brenonoccioli.clients

import io.micronaut.core.annotation.Introspected

@Introspected
data class InstituicaoResponse(
    val nome: String,
    val ispb: String
)
