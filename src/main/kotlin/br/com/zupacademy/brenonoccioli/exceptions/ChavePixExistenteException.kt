package br.com.zupacademy.brenonoccioli.exceptions

class ChavePixExistenteException() : Exception() {
    override val message: String?
        get() = "chave pix existente"
}
