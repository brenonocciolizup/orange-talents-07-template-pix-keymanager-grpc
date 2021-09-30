package br.com.zupacademy.brenonoccioli.repository

import br.com.zupacademy.brenonoccioli.model.ChavePix
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, Long> {
    fun existsByChave(chave: String): Boolean
    //fun existsByidPix(idPix: String): Boolean

    fun findByIdPix(idPix: String): Optional<ChavePix>


}
