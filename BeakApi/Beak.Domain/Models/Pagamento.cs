namespace Beak.Domain.Models
{
    public class Pagamento : Transacao
    {
        public Pagamento(Cliente recebedor, Cliente pagador, Conta conta): base(recebedor, pagador, conta)
        {
        }

        public override void Executa()
        {
            Recebedor.Recebe(Conta);
            Pagador.Paga(Conta);
        }
    }
}
