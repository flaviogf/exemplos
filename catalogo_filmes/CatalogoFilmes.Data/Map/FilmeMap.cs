using MongoDB.Bson.Serialization;
using MongoDB.Bson.Serialization.IdGenerators;
using CatalogoFilmes.Domain.Models;

namespace CatalogoFilmes.Data.Map
{

    public class FilmeMap
    {

        public static void Mapping()
        {
            BsonClassMap.RegisterClassMap<Filme>(cm =>
            {
                cm.AutoMap();
                cm.MapIdField(f => f.Id)
                   .SetIdGenerator(StringObjectIdGenerator.Instance);
            });
        }
    }
}