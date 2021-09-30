package br.com.zupacademy.brenonoccioli.endpointGrpc.remove

import br.com.zupacademy.brenonoccioli.RemoveChavePixGrpcServiceGrpc
import br.com.zupacademy.brenonoccioli.RemoveChavePixRequest
import br.com.zupacademy.brenonoccioli.RemoveChavePixResponse
import br.com.zupacademy.brenonoccioli.endpointGrpc.registra.toChaveParaRemover
import br.com.zupacademy.brenonoccioli.handler.ErrorHandler
import io.grpc.stub.StreamObserver
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@ErrorHandler
@Singleton
class RemoveChavePixGrpc(val removeChavePix: RemoveChavePixService):
    RemoveChavePixGrpcServiceGrpc.RemoveChavePixGrpcServiceImplBase() {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun remove(request: RemoveChavePixRequest, responseObserver: StreamObserver<RemoveChavePixResponse>?) {
        log.info("Solicitação de remoção de chave")

        val chaveParaRemover = request.toChaveParaRemover()
        removeChavePix.remove(chaveParaRemover)

        val resp = RemoveChavePixResponse.newBuilder().setMessage("Chave excluída com sucesso").build()
        responseObserver?.onNext(resp)
        responseObserver?.onCompleted()
    }
}