using AppXamarin3.Models;
using AppXamarin3.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace AppXamarin3.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TelaPessoa : ContentPage
    {
        public TelaPessoa(Pessoa pessoa)
        {
            InitializeComponent();
            BindingContext = new TelaPessoaViewModel(pessoa);
        }
    }
}