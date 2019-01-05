using System.Collections.Generic;

namespace CatalogoFilmes.Domain.Models
{

    public class Filme
    {

        public string Id { get; set; }

        public string Nome { get; set; }

        public string Descricao { get; set; }

        public int AnoLancamento { get; set; }

        public int Duracao { get; set; }
    }
}