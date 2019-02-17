using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using MongoDB.Driver;
using CatalogoFilmes.Domain.Models;

namespace CatalogoFilmes.Data.Repository
{

    public class FilmeRepository : IFilmeRepository
    {
        private readonly IContextoDb contexto;

        public FilmeRepository(IContextoDb contexto)
        {
            this.contexto = contexto;
        }

        public IEnumerable<Filme> Listar(int pagina, int quantidade)
        {
            return contexto.Filmes
                .AsQueryable()
                .Skip((pagina - 1) * quantidade)
                .Take(quantidade)
                .ToList();
        }

        public async Task Inserir(Filme filme)
        {
            await contexto.Filmes
                .InsertOneAsync(filme);
        }
    }
}