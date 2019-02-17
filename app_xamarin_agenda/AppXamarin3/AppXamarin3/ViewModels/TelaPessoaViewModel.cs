using AppXamarin3.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppXamarin3.ViewModels
{
    public class TelaPessoaViewModel
    {
        public string Nome { get; set; }
        public string Email { get; set; }
        public int Idade { get; set; }

        public TelaPessoaViewModel(Pessoa pessoa)
        {
            this.Nome = pessoa.Nome;
            this.Email = pessoa.Email;
            this.Idade = pessoa.Idade;
        }
    }
}