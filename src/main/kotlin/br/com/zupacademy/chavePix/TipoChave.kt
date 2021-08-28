package br.com.zupacademy.chavePix

enum class TipoChave {
    CPF{
      override fun validaValor(valor: String?): Boolean{
          if(valor.isNullOrBlank()) return false
          return "^[0-9]{11}\$".toRegex().matches(valor)
      }
    },
    CELULAR{
        override fun validaValor(valor: String?): Boolean {
            if(valor.isNullOrBlank()) return false
            return "^\\+[1-9][0-9]\\d{1,14}$".toRegex().matches(valor)
        }
    },
    EMAIL{
        override fun validaValor(valor: String?): Boolean {
            if(valor.isNullOrBlank()) return false
            return "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                .toRegex().matches(valor)
        }
    },
    CHAVE_ALEATORIA{
        override fun validaValor(valor: String?): Boolean {
            return valor.isNullOrBlank()
        }
    };

    abstract fun validaValor(valor: String?): Boolean
}