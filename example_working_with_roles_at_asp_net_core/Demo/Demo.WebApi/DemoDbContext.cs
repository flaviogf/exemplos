using Demo.WebApi.Models;
using Microsoft.EntityFrameworkCore;
using System;

namespace Demo.WebApi
{
    public class DemoDbContext : DbContext
    {
        public DemoDbContext(DbContextOptions<DemoDbContext> options) : base(options)
        {
        }

        public DbSet<User> Users { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            #region User

            modelBuilder.Entity<User>().HasAlternateKey(it => it.Username);
            modelBuilder.Entity<User>().Property(it => it.Username).IsRequired();

            modelBuilder.Entity<User>().Property(it => it.PasswordHash).IsRequired();

            modelBuilder.Entity<User>().Property(it => it.Role).IsRequired();

            #endregion

            #region Seeds

            modelBuilder.Entity<User>().HasData
            (
                new User
                {
                    Id = Guid.NewGuid(),
                    Username = "admin",
                    PasswordHash = "$2a$11$3UoNmHJrGqiyfZW6xtsYDezra9m0D9pYu6ahAE/JQ54EoB80JDfgi",
                    Role = "staff"
                }
            );

            #endregion
        }
    }
}
