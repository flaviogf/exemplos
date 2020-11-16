using HealthChecks.UI.Client;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Diagnostics.HealthChecks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Diagnostics.HealthChecks;
using Microsoft.Extensions.Hosting;

namespace HealthChecksDemo
{
    public class Startup
    {
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddSingleton<ResponseTimeHealthCheck>();

            services.AddHealthChecks()
                .AddCheck<ResponseTimeHealthCheck>("ResponseTimeService", tags: new string[] { "service" })
                .AddCheck("Database", () => HealthCheckResult.Healthy("The check of the database worked"), new string[] { "database", "sql" });

            services.AddControllers();
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseRouting();

            app.UseEndpoints(it =>
            {
                it.MapHealthChecks("/health", new HealthCheckOptions
                {
                    ResponseWriter = UIResponseWriter.WriteHealthCheckUIResponse
                });

                it.MapHealthChecks("/health/quick", new HealthCheckOptions
                {
                    Predicate = (_) => false
                });

                it.MapHealthChecks("/health/service", new HealthCheckOptions
                {
                    Predicate = (r) => r.Tags.Contains("service"),
                    ResponseWriter = UIResponseWriter.WriteHealthCheckUIResponse
                });

                it.MapControllers();
            });
        }
    }
}
