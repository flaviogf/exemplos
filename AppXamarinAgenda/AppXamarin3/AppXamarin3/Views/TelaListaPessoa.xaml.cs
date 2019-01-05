using AppXamarin3.Models;
using AppXamarin3.ViewModels;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace AppXamarin3.Views
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TelaListaPessoa : ContentPage
    {
        public TelaListaPessoa()
        {
            InitializeComponent();
            BindingContext = new TelaListaPessoaViewModel();
        }

        private void ListView_ItemTapped(object sender, ItemTappedEventArgs e)
        {
            var pessoa = e.Item as Pessoa;
            Navigation.PushAsync(new TelaPessoa(pessoa));
        }
    }
}