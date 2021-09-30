package br.com.zupacademy.brenonoccioli.endpointGrpc.registra

import br.com.zupacademy.brenonoccioli.RegistraChavePixRequest
import br.com.zupacademy.brenonoccioli.RemoveChavePixRequest
import br.com.zupacademy.brenonoccioli.TipoChavePix
import br.com.zupacademy.brenonoccioli.TipoConta
import br.com.zupacademy.brenonoccioli.endpointGrpc.remove.ChaveParaRemover
import br.com.zupacademy.brenonoccioli.model.TipoChave
import br.com.zupacademy.brenonoccioli.model.TipoDeConta

fun RegistraChavePixRequest.toNovaChavePix(): NovaChavePix {
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

fun RemoveChavePixRequest.toChaveParaRemover(): ChaveParaRemover {
    return ChaveParaRemover(idPix, idCliente)
}

