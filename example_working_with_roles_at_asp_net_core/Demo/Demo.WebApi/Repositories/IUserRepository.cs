using Demo.WebApi.Models;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Demo.WebApi.Repositories
{
    public interface IUserRepository
    {
        Task<User> Add(User user);

        Task<User> Get(Guid id);

        Task<User> GetByUsername(string username);

        Task<IEnumerable<User>> GetAll();

        Task SaveChanges();
    }
}
