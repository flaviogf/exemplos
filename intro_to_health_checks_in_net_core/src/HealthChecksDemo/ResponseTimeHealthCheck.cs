using System;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Diagnostics.HealthChecks;

namespace HealthChecksDemo
{
    public class ResponseTimeHealthCheck : IHealthCheck
    {
        private readonly Random _random = new Random();

        public Task<HealthCheckResult> CheckHealthAsync(HealthCheckContext context, CancellationToken cancellationToken = default)
        {
            var responseTime = _random.Next(1, 300);

            if (responseTime > 200)
            {
                return Task.FromResult(HealthCheckResult.Unhealthy($"The response time is unacceptable ({responseTime})"));
            }

            if (responseTime > 100)
            {
                return Task.FromResult(HealthCheckResult.Degraded($"The response time is a bit slow ({responseTime})"));
            }

            return Task.FromResult(HealthCheckResult.Healthy($"The response time looks good ({responseTime})"));
        }
    }
}
