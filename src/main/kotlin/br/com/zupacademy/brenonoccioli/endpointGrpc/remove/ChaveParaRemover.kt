package br.com.zupacademy.brenonoccioli.endpointGrpc.remove

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotEmpty

@Introspected
data class ChaveParaRemover(@field:NotEmpty val idPix: String,
                            @field:NotEmpty val idCliente: String)
