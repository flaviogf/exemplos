using System.Collections.Generic;
using FluentValidation.Results;

namespace CatalogoFilmes.Service.Extensions
{

    public static class FluentValidationExtensions
    {

        public static IEnumerable<string> ListaErros(this ValidationResult result)
        {
            var listaErros = new List<string>();
            foreach (var erro in result.Errors)
            {
                listaErros.Add(erro.ErrorMessage);
            }
            return listaErros;
        }
    }
}