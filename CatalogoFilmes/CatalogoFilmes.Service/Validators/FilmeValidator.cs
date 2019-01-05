using System;
using FluentValidation;
using CatalogoFilmes.Domain.Models;
using CatalogoFilmes.Service.Validators;

namespace CatalogoFilmes.Service.Validators
{

    public class FilmeValidator : AbstractValidator<Filme>, IFilmeValidator
    {

        public FilmeValidator()
        {
            RuleFor(filme => filme.Nome)
                .Length(3, 50)
                .WithMessage("{PropertyName} deve ter no minimo {MinLength} e no maxímo {MaxLength} caracteres");
            RuleFor(filme => filme.Descricao)
                .Length(3, 100)
                .WithMessage("{PropertyName} deve ter no minimo {MinLength} e no maxímo {MaxLength} caracteres");
            RuleFor(filme => filme.AnoLancamento)
                .LessThanOrEqualTo(DateTime.Now.Year)
                .WithMessage("{PropertyName} deve ser menor ou igual a {ComparisonValue}");
            RuleFor(filme => filme.Duracao)
                .GreaterThan(0)
                .WithMessage("{PropertyName} deve ser maior que {ComparisonValue}");
        }
    }
}