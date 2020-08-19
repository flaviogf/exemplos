using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace Sample.CommandLine
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var host = Host
                .CreateDefaultBuilder(args)
                .ConfigureServices((context, services) =>
                {
                    services.AddScoped<IGreetingService, GreetingService>();
                })
                .Build();

            host.Services.GetRequiredService<IGreetingService>().Run();

            host.Run();
        }
    }
}
