using System.Collections.Generic;
using System.Threading.Tasks;
using CatalogoFilmes.Domain.Models;

namespace CatalogoFilmes.Data.Repository
{

    public interface IFilmeRepository
    {

        IEnumerable<Filme> Listar(int pagina, int quantidade);

        Task Inserir(Filme filme);
    }
}