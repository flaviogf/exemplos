using AppXamarin3.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppXamarin3.ViewModels
{
    public class TelaListaPessoaViewModel
    {
        public List<Pessoa> Pessoas { get; set; }

        public TelaListaPessoaViewModel()
        {
            this.Pessoas = new List<Pessoa>();
            this.Pessoas.Add(new Pessoa { Nome="Flávio", Email="flavio@email.com", Idade=20 });
            this.Pessoas.Add(new Pessoa { Nome = "Fernando", Email="fernando@email.com", Idade=25 });
            this.Pessoas.Add(new Pessoa { Nome="Fátima", Email="fatima@email.com", Idade=55 });
            this.Pessoas.Add(new Pessoa { Nome="Luis Fernando", Email="luis@email.com", Idade=44 });
        }
    }
}
