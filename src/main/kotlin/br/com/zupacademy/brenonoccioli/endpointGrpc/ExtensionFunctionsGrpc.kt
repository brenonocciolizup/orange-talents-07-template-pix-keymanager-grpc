package br.com.zupacademy.brenonoccioli.endpointGrpc

import br.com.zupacademy.brenonoccioli.RegistraChavePixRequest
import br.com.zupacademy.brenonoccioli.TipoChavePix
import br.com.zupacademy.brenonoccioli.TipoConta
import br.com.zupacademy.brenonoccioli.model.TipoChave
import br.com.zupacademy.brenonoccioli.model.TipoDeConta
import br.com.zupacademy.brenonoccioli.service.NovaChavePix
import java.util.*

fun RegistraChavePixRequest.toNovaChavePix(): NovaChavePix{
    return NovaChavePix(
        clienteId = clienteId,
        tipoDeConta = when(tipoConta){
            TipoConta.CONTA_DESCONHECIDA -> null
            else -> TipoDeConta.valueOf(tipoConta.name)
        },
        tipoChave = when(tipoChavePix){
            TipoChavePix.CHAVE_DESCONHECIDA -> null
            else -> TipoChave.valueOf(tipoChavePix.name)
        },
        chave = chave
    )
}
