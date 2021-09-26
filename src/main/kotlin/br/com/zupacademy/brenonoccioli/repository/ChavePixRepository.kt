package br.com.zupacademy.brenonoccioli.repository

import br.com.zupacademy.brenonoccioli.model.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, Long> {
    fun existsByChave(chave: String): Boolean

}
