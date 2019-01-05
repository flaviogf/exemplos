using System.Collections.Generic;
using Flunt.Validations;

namespace Beak.Domain.Models
{
    public class Cliente : Model
    {
        private List<Conta> _contasRecebidas = new List<Conta>();

        private List<Conta> _contaPagas = new List<Conta>();

        public string Nome { get; }

        public IReadOnlyList<Conta> ContasRecebidas => _contasRecebidas;

        public IReadOnlyList<Conta> ContasPagas => _contaPagas;

        public int QuantidadeContasRecebidas => _contasRecebidas.Count;

        public int QuantidadeContasPagas => _contaPagas.Count;

        public Cliente(string nome)
        {
            AddNotifications(new Contract()
                .Requires()
                .HasMinLen(nome, 3, nameof(Nome), $"{nameof(Nome)} inválido")
            );

            Nome = nome;
        }

        public void Recebe(Conta conta)
        {
            _contasRecebidas.Add(conta);
        }

        public void Paga(Conta conta)
        {
            _contaPagas.Add(conta);
        }
    }
}
