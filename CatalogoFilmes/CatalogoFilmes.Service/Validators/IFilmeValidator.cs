using CatalogoFilmes.Domain.Models;
using FluentValidation.Results;

namespace CatalogoFilmes.Service.Validators
{
    public interface IFilmeValidator
    {

        ValidationResult Validate(Filme filme);
    }
}