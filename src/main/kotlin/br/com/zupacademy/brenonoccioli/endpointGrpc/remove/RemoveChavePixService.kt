package br.com.zupacademy.brenonoccioli.endpointGrpc.remove

import br.com.zupacademy.brenonoccioli.exceptions.ChaveNaoExisteException
import br.com.zupacademy.brenonoccioli.exceptions.ExclusaoNaoAutorizadaException
import br.com.zupacademy.brenonoccioli.handler.ErrorHandler
import br.com.zupacademy.brenonoccioli.repository.ChavePixRepository
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
class RemoveChavePixService(val repository: ChavePixRepository) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun remove(@Valid chaveParaRemover: ChaveParaRemover){

        val possivelChave = repository.findByIdPix(chaveParaRemover.idPix)
        if(possivelChave.isEmpty){
            log.error("Chave não existe no banco")
            throw ChaveNaoExisteException("aquiiiiiiii")
        } else if(possivelChave.get().idCliente != chaveParaRemover.idCliente){
            log.error("id do cliente não é o da chave")
            throw ExclusaoNaoAutorizadaException("não autorizado")
        }

        val chavePix = possivelChave.get()
        repository.delete(chavePix)
        log.info("Chave ${chavePix.idPix} excluída com sucesso")
    }
}
