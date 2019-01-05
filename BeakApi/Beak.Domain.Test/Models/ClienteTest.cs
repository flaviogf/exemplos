using Beak.Domain.Models;
using Xunit;

namespace Beak.Domain.Test.Models
{
    public class ClienteTest
    {
        [Theory]
        [InlineData("Flavio")]
        [InlineData("Fernando")]
        public void DeveRetornarUmClienteValidoQuandoInformarNomeValido(string nome)
        {
            var cliente = new Cliente(nome);
            Assert.Equal(nome, cliente.Nome);
            Assert.True(cliente.Valid);
        }

        [Theory]
        [InlineData("")]
        [InlineData(" ")]
        public void DeveRetornarUmClienteInvalidoQuandoInformarUmNomeInvalido(string nome)
        {
            var cliente = new Cliente(nome);
            Assert.True(cliente.Invalid);
        }

        [Fact]
        public void DeveReceberUmContaQuandoInfomarUmaContaParaReceber()
        {
            var valor = 200.55M;
            var conta = new Conta(valor);
            var recebedor = new Cliente("Fernando");
            recebedor.Recebe(conta);
            Assert.Equal(1, recebedor.QuantidadeContasRecebidas);
        }
    }
}
