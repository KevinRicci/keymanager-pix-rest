package br.com.zupacademy.exception

data class DetalhesErro(
    val mensagem: String?,
    val erros: List<Erro>?
) {
}