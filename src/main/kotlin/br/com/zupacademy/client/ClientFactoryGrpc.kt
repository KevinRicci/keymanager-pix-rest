package br.com.zupacademy.client

import br.com.zupacademy.KeyManagerPixServiceCadastraGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class ClientFactoryGrpc {

    @Singleton
    fun clienteCadastraChavePix(@GrpcChannel("pix") channel: ManagedChannel): KeyManagerPixServiceCadastraGrpc.KeyManagerPixServiceCadastraBlockingStub{
        return KeyManagerPixServiceCadastraGrpc.newBlockingStub(channel)
    }
}