package br.com.zupacademy.chavePix.cadastra

import br.com.zupacademy.chavePix.TipoChave
import br.com.zupacademy.chavePix.TipoConta
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CadastraChavePixRequestTest{

    @Nested
    inner class ChaveAleatoriaTest{
        @Test
        fun `deve ser válido quando não houver valor`(){
            val chaveRequest = CadastraChavePixRequest(
                TipoChave.CHAVE_ALEATORIA,
                "",
                TipoConta.CONTA_CORRENTE
            )

            assertTrue(chaveRequest.tipoChave.validaValor(chaveRequest.valorChave!!))
        }

        @Test
        fun `deve ser válido quando valor for nulo`(){
            val chaveRequest = CadastraChavePixRequest(
                TipoChave.CHAVE_ALEATORIA,
                null,
                TipoConta.CONTA_POUPANCA
            )

            assertTrue(chaveRequest.tipoChave.validaValor(chaveRequest.valorChave))
        }

        @Test
        fun `deve ser inválido quando houver valor`(){
            val chaveRequest = CadastraChavePixRequest(
                TipoChave.CHAVE_ALEATORIA,
                "não pode passar valor aqui quando for aleatória",
                TipoConta.CONTA_CORRENTE
            )

            assertFalse(chaveRequest.tipoChave.validaValor(chaveRequest.valorChave!!))
        }
    }

    @Nested
    inner class CpfTest{
        @Test
        fun `deve ser válido quando cpf for correto`(){
            val request = CadastraChavePixRequest(
                TipoChave.CPF,
                "69298129009",
                TipoConta.CONTA_CORRENTE
            )

            assertTrue(request.tipoChave.validaValor(request.valorChave))
        }

        @Test
        fun `deve ser inválido quando cpf for nulo`(){
            val request = CadastraChavePixRequest(
                TipoChave.CPF,
                null,
                TipoConta.CONTA_POUPANCA
            )

            assertFalse(request.tipoChave.validaValor(request.valorChave))
        }

        @Test
        fun `deve ser inválido quando cpf for incorreto`(){
            val request = CadastraChavePixRequest(
                TipoChave.CPF,
                "12345",
                TipoConta.CONTA_POUPANCA
            )

            assertFalse(request.tipoChave.validaValor(request.valorChave))
        }
    }

    @Nested
    inner class EmailTest{
        @Test
        fun `deve ser válido quando email for correto`(){
            val request = CadastraChavePixRequest(
                TipoChave.EMAIL,
                "kevin@gmail.com",
                TipoConta.CONTA_CORRENTE
            )

            assertTrue(request.tipoChave.validaValor(request.valorChave))
        }

        @Test
        fun `deve ser inválido quando email for nulo`(){
            val request = CadastraChavePixRequest(
                TipoChave.EMAIL,
                null,
                TipoConta.CONTA_CORRENTE
            )

            assertFalse(request.tipoChave.validaValor(request.valorChave))
        }

        @Test
        fun `deve ser inválido quando email for incorreto`(){
            val request = CadastraChavePixRequest(
                TipoChave.EMAIL,
                "kevingmail.com",
                TipoConta.CONTA_CORRENTE
            )

            assertFalse(request.tipoChave.validaValor(request.valorChave))
        }
    }

    @Nested
    inner class CelularTest{
        @Test
        fun `deve ser válido quando celular for correto`(){
            val request = CadastraChavePixRequest(
                TipoChave.CELULAR,
                "+5511998736452",
                TipoConta.CONTA_POUPANCA
            )

            assertTrue(request.tipoChave.validaValor(request.valorChave))
        }

        @Test
        fun `deve ser inválido quando celular for nulo`(){
            val request = CadastraChavePixRequest(
                TipoChave.CELULAR,
                null,
                TipoConta.CONTA_POUPANCA
            )

            assertFalse(request.tipoChave.validaValor(request.valorChave))
        }

        @Test
        fun `deve ser inválido quando celular for incorreto`(){
            val request = CadastraChavePixRequest(
                TipoChave.CELULAR,
                "11998736452",
                TipoConta.CONTA_POUPANCA
            )

            assertFalse(request.tipoChave.validaValor(request.valorChave))
        }
    }
}