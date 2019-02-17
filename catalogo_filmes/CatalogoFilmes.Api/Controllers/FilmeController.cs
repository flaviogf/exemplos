using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using CatalogoFilmes.Data.Repository;
using CatalogoFilmes.Domain.Models;
using CatalogoFilmes.Service.Extensions;
using CatalogoFilmes.Service.Validators;

namespace CatalogoFilmes.Api.Controllers
{

    [Route("api/[controller]")]
    public class FilmeController : Controller
    {
        private readonly IFilmeValidator validator;

        private readonly IFilmeRepository repository;

        public FilmeController(IFilmeValidator validator, IFilmeRepository repository)
        {
            this.validator = validator;
            this.repository = repository;
        }

        [HttpGet]
        public IActionResult Listar([FromQuery] int pagina = 1, [FromQuery] int quantidade = 10)
        {
            var filmes = repository.Listar(pagina, quantidade);
            return Ok(filmes);
        }

        [HttpPost]
        public async Task<IActionResult> Inserir([FromBody] Filme filme)
        {
            var validacao = validator.Validate(filme);
            if (validacao.IsValid)
            {
                await repository.Inserir(filme);
                return NoContent();
            }
            var listaErros = validacao.ListaErros();
            return BadRequest(listaErros);
        }
    }
}