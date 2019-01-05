using System;
using Flunt.Validations;

namespace Beak.Domain.Models
{
    public class Conta: Model
    {
        public decimal Valor { get; }

        public DateTime Data { get; } = DateTime.Now;

        public Conta(decimal valor)
        {
            AddNotifications(new Contract()
                .Requires()
                .IsGreaterThan(valor, 0, nameof(Valor), $"{nameof(Valor)} inválido")
            );

            Valor = valor;
        }

        public Conta(decimal valor, DateTime data) : this(valor)
        {
            Data = data;
        }
    }
}
