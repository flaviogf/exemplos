using MongoDB.Driver;
using CatalogoFilmes.Domain.Models;
using CatalogoFilmes.Data.Map;

namespace CatalogoFilmes.Data
{

    public class ContextoDb : IContextoDb
    {

        public IMongoClient Client { get; }

        public IMongoDatabase Database => Client.GetDatabase("CatalogoFilmes");

        public IMongoCollection<Filme> Filmes => Database.GetCollection<Filme>("Filmes");

        public ContextoDb(string connectionString)
        {
            MappingClass();
            Client = new MongoClient(connectionString);
        }

        private void MappingClass()
        {
            FilmeMap.Mapping();
        }
    }
}