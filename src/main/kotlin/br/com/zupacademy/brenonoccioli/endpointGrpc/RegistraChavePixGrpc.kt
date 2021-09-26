package br.com.zupacademy.brenonoccioli.endpointGrpc

import br.com.zupacademy.brenonoccioli.RegistraChavePixGrpcServiceGrpc
import br.com.zupacademy.brenonoccioli.RegistraChavePixRequest
import br.com.zupacademy.brenonoccioli.RegistraChavePixResponse
import br.com.zupacademy.brenonoccioli.handler.ErrorHandler
import br.com.zupacademy.brenonoccioli.service.RegistraChavePixService
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@ErrorHandler
@Singleton
class RegistraChavePixGrpc(val service: RegistraChavePixService):
    RegistraChavePixGrpcServiceGrpc.RegistraChavePixGrpcServiceImplBase() {

        private val log = LoggerFactory.getLogger(this::class.java)

        override fun registra(
            request: RegistraChavePixRequest,
            responseObserver: StreamObserver<RegistraChavePixResponse>
        ) {
            val novaChave = request.toNovaChavePix()
            val chaveRegistrada = service.registra(novaChave)
            log.info("IdPix da chave registrada: ${chaveRegistrada.idPix}")
            responseObserver.onNext(RegistraChavePixResponse.newBuilder()
                .setIdPix(chaveRegistrada.idPix).build())
            responseObserver.onCompleted()
        }
}