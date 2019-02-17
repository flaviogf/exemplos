using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using CatalogoFilmes.Data;
using CatalogoFilmes.Data.Repository;
using CatalogoFilmes.Service.Validators;

namespace CatalogoFilmes.Api
{
    public class Startup
    {

        public IConfiguration Configuration { get; }

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc();
            var connectionString = Configuration.GetConnectionString("MongoDb");
            services.AddSingleton<IContextoDb>(
                new ContextoDb(connectionString)
            );
            services.AddScoped<IFilmeValidator, FilmeValidator>();
            services.AddScoped<IFilmeRepository, FilmeRepository>();
        }

        public void Configure(IApplicationBuilder app, IHostingEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            app.UseMvc();
        }
    }
}
