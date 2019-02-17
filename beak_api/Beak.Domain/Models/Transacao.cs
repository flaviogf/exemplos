using Flunt.Validations;

namespace Beak.Domain.Models
{
    public abstract class Transacao : Model
    {
        public Cliente Recebedor { get; }

        public Cliente Pagador { get; }

        public Conta Conta { get; }

        public Transacao(Cliente recebedor, Cliente pagador, Conta conta)
        {
            AddNotifications(new Contract()
                .Requires()
                .IsTrue(recebedor.Valid, nameof(Recebedor), $"{nameof(Recebedor)} inválido")
                .IsTrue(pagador.Valid, nameof(Pagador), $"{nameof(Pagador)} inválido")
                .IsTrue(conta.Valid, nameof(Conta), $"{nameof(Conta)} inválida")
            );

            Recebedor = recebedor;
            Pagador = pagador;
            Conta = conta;
        }

        public abstract void Executa();
    }
}
