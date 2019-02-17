using Beak.Domain.Models;
using Xunit;
using System.Collections.Generic;

namespace Beak.Domain.Test.Models
{
    public class ContaTest
    {
        [Fact]
        public void DeveRetornarUmaContaValidaQuandoInformarUmValorValido()
        {
            var valor = 200.55M;
            var conta = new Conta(valor);
            Assert.True(conta.Valid);
        }

        [Theory]
        [InlineData(0)]
        [InlineData(-1)]
        public void DeveRetornarUmaContaInvalidaQuandoInformarUmValorInvalido(decimal valor)
        {
            var conta = new Conta(valor);
            Assert.True(conta.Invalid);
        }
    }
}
