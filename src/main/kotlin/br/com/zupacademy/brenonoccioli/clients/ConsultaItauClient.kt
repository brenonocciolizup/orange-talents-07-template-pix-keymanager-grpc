package br.com.zupacademy.brenonoccioli.clients

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("\${url_consulta_itau}")
interface ConsultaItauClient {
    @Get("/{clienteId}/contas")
    fun consultaClientesItau(@PathVariable clienteId: String, @QueryValue tipo: String): HttpResponse<ConsultaClienteResponse>
}