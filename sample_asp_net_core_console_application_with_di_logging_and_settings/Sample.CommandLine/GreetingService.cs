using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using System.Linq;

namespace Sample.CommandLine
{
    public class GreetingService : IGreetingService
    {
        private readonly IConfiguration _configuration;
        private readonly ILogger<IGreetingService> _logger;

        public GreetingService(IConfiguration configuration, ILogger<IGreetingService> logger)
        {
            _configuration = configuration;
            _logger = logger;
        }

        public void Run()
        {
            var start = _configuration.GetValue<int>("GreetingService:Start");

            var end = _configuration.GetValue<int>("GreetingService:End");

            _logger.LogInformation($"Logging from {start} until {end}");

            Enumerable
                .Range(start, end)
                .Select(it => it.ToString())
                .ToList()
                .ForEach((it) => _logger.LogInformation(it));
        }
    }
}
