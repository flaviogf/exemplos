using Beak.Domain.Models;
using Xunit;

namespace Beak.Domain.Test.Models
{
    public class PagamentoTest
    {
        [Fact]
        public void DeveRealizarPagamentoComSucessoQuandoInformarTodosParametrosValidos()
        {
            var recebedor = new Cliente("Flavio");
            var pagador = new Cliente("Fernando");
            var conta = new Conta(300.50M);
            new Pagamento(recebedor, pagador, conta).Executa();
            Assert.Equal(1, recebedor.QuantidadeContasRecebidas);
            Assert.Equal(1, pagador.QuantidadeContasPagas);
        }
    }
}
