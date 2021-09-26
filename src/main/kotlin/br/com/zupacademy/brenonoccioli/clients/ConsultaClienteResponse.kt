package br.com.zupacademy.brenonoccioli.clients

import br.com.zupacademy.brenonoccioli.model.ContaAssociada
import io.micronaut.core.annotation.Introspected

@Introspected
data class ConsultaClienteResponse (
    val tipo: String,
    val instituicao: InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: TitularResponse
    ){

    fun toModel(): ContaAssociada{
        return ContaAssociada(
            instituicaoNome = instituicao.nome,
            instituicaoIspb =  instituicao.ispb,
            nomeDoTitular = titular.nome,
            documentoDoTitular = titular.cpf,
            agencia = agencia,
            numeroDaConta = numero)
    }
}
