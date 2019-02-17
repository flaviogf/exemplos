using MongoDB.Driver;
using CatalogoFilmes.Domain.Models;

namespace CatalogoFilmes.Data
{

    public interface IContextoDb
    {

        IMongoClient Client { get; }

        IMongoDatabase Database { get; }

        IMongoCollection<Filme> Filmes { get; }
    }
}