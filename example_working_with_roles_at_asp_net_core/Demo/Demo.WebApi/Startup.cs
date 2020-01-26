using AutoMapper;
using Demo.WebApi.Models;
using Demo.WebApi.Repositories;
using Demo.WebApi.Services;
using Demo.WebApi.ViewModels;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.IdentityModel.Tokens;
using System.Reflection;
using System.Text;

namespace Demo.WebApi
{
    public class Startup
    {
        private readonly IConfiguration _configuration;

        public Startup(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<DemoDbContext>(it => it.UseSqlServer(_configuration.GetConnectionString("DemoDbContext")));

            services.AddAutoMapper(it =>
            {
                it.CreateMap<User, UserViewModel>().ReverseMap();
            },
            Assembly.GetExecutingAssembly());

            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
                    .AddJwtBearer(it =>
                    {
                        it.TokenValidationParameters.IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Authorization:JwtBearer:SecretKey"]));
                        it.TokenValidationParameters.ValidateAudience = false;
                        it.TokenValidationParameters.ValidateIssuer = false;
                        it.TokenValidationParameters.ValidateIssuerSigningKey = true;
                    });

            services.AddScoped<IHash, Bcrypt>();

            services.AddScoped<IAuth, Jwt>();

            services.AddScoped<IUserRepository, EFUserRepository>();

            services.AddControllers();
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();

            app.UseAuthentication();

            app.UseAuthorization();

            app.UseEndpoints(it => it.MapControllers());
        }
    }
}
