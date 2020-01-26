using Demo.WebApi.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Demo.WebApi.Repositories
{
    public class EFUserRepository : IUserRepository
    {
        private readonly DemoDbContext _context;

        public EFUserRepository(DemoDbContext context)
        {
            _context = context;
        }

        public async Task<User> Add(User user)
        {
            await _context.Users.AddAsync(user);

            return user;
        }

        public async Task<User> Get(Guid id)
        {
            return await _context.Users.FirstOrDefaultAsync(it => it.Id == id);
        }

        public async Task<User> GetByUsername(string username)
        {
            return await _context.Users.FirstOrDefaultAsync(it => it.Username == username);
        }

        public async Task<IEnumerable<User>> GetAll()
        {
            return await _context.Users.ToListAsync();
        }

        public async Task SaveChanges()
        {
            await _context.SaveChangesAsync();
        }
    }
}
