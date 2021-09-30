package br.com.zupacademy.brenonoccioli.endpointGrpc.registra

import br.com.zupacademy.brenonoccioli.RegistraChavePixGrpcServiceGrpc
import br.com.zupacademy.brenonoccioli.RegistraChavePixRequest
import br.com.zupacademy.brenonoccioli.TipoChavePix
import br.com.zupacademy.brenonoccioli.TipoConta
import br.com.zupacademy.brenonoccioli.clients.ConsultaClienteResponse
import br.com.zupacademy.brenonoccioli.clients.ConsultaItauClient
import br.com.zupacademy.brenonoccioli.clients.InstituicaoResponse
import br.com.zupacademy.brenonoccioli.clients.TitularResponse
import br.com.zupacademy.brenonoccioli.model.ContaAssociada
import br.com.zupacademy.brenonoccioli.model.TipoChave
import br.com.zupacademy.brenonoccioli.model.TipoDeConta
import br.com.zupacademy.brenonoccioli.repository.ChavePixRepository
import io.grpc.ManagedChannel
import io.grpc.StatusRuntimeException
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.http.HttpResponse
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@MicronautTest(transactional = false)
internal class RegistraChavePixServiceTest(@field:Inject val serviceRegistra: RegistraChavePixService,
                                           @field:Inject val repository: ChavePixRepository){

    @field:Inject
    private lateinit var client: ConsultaItauClient


    val consultaResponse = ConsultaClienteResponse(
        tipo = "CONTA_CORRENTE",
        instituicao = InstituicaoResponse("ITAÚ UNIBANCO S.A.", "60701190"),
        agencia = "0001",
        numero = "291900",
        titular = TitularResponse("c56dfef4-7901-44fb-84e2-a2cefb157890", "Rafael M C Ponte", "02467781054")
    )

    private val CLIENT_ID = "c56dfef4-7901-44fb-84e2-a2cefb157890"
    private val CHAVE_CPF = "11211211212"

    lateinit var novaChavePix: NovaChavePix

    @BeforeEach
    fun setup(){
        repository.deleteAll()
        novaChavePix = NovaChavePix(CLIENT_ID, TipoDeConta.CONTA_CORRENTE, TipoChave.CPF, CHAVE_CPF)
    }

    @Test
    fun `deve salvar chave pix cpf`(){
        //cenário
        Mockito.`when`(client.consultaClientesItau(novaChavePix.clienteId, novaChavePix.tipoDeConta.toString()))
            .thenReturn(HttpResponse.ok(consultaResponse))

        //ação
        val chavePix = serviceRegistra.registra(novaChavePix)
        val chaveCadastrada = repository.findByIdPix(chavePix.idPix)

        //verificação
        assertEquals(chavePix.chave, CHAVE_CPF)
        assertEquals(chavePix, chaveCadastrada)
    }


    @Factory
    class Clients {
        @Singleton
        fun blockingStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel):
                RegistraChavePixGrpcServiceGrpc.RegistraChavePixGrpcServiceBlockingStub {
            return RegistraChavePixGrpcServiceGrpc.newBlockingStub(channel)
        }
    }

    @MockBean(ConsultaItauClient::class)
    fun consultaItauClientMock(): ConsultaItauClient{
        return Mockito.mock(ConsultaItauClient::class.java)
    }

}

