using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System.Threading;
using System.Threading.Tasks;

namespace Example.BackgroundTask
{
    public class DoSomethingHostedService : IHostedService
    {
        private readonly ILogger<DoSomethingHostedService> _logger;

        public DoSomethingHostedService(ILogger<DoSomethingHostedService> logger)
        {
            _logger = logger;
        }

        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("Start doing something");

            return Task.CompletedTask;
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("Stop doing something");

            return Task.CompletedTask;
        }
    }
}
