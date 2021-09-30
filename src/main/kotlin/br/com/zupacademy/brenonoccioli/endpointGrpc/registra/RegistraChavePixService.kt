package br.com.zupacademy.brenonoccioli.endpointGrpc.registra

import br.com.zupacademy.brenonoccioli.clients.ConsultaItauClient
import br.com.zupacademy.brenonoccioli.exceptions.ChavePixExistenteException
import br.com.zupacademy.brenonoccioli.model.ChavePix
import br.com.zupacademy.brenonoccioli.repository.ChavePixRepository
import io.micronaut.validation.Validated
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class RegistraChavePixService(val repository: ChavePixRepository,
                              val client: ConsultaItauClient) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registra(@Valid novaChave: NovaChavePix?): ChavePix{
        if(repository.existsByChave(novaChave!!.chave)){
            throw ChavePixExistenteException()
        }

        log.info("consultando erp itaú")
        val response = client.consultaClientesItau(novaChave.clienteId, novaChave.tipoDeConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não encontrado na instituição")

        val chavePix = novaChave.toModel(conta)

        repository.save(chavePix)
        log.info("Chave Pix '${chavePix.idPix}' criada com sucesso")
        return chavePix
    }
}
