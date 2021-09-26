package br.com.zupacademy.brenonoccioli.exceptions

class ChavePixExistenteException(s: String) : Exception() {
    override val message: String?
        get() = "Chave Pix já cadastrada"
}
