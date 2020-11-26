using System;
using System.Linq;
using System.Threading.Tasks;

namespace BlazorApp.Data
{
    public class WeatherForecastService
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        public Task<WeatherForecast[]> GetForecastAsync(DateTime startDate)
        {
            var random = new Random();

            var result = Enumerable
                .Range(1, 5)
                .Select((it) => new WeatherForecast
                {
                    Date = DateTime.Today.AddDays(it),
                    TemperatureC = random.Next(-20, 55)
                })
                .Select((it) =>
                {
                    it.Summary = it.TemperatureF switch
                    {
                        < 0 => "Well below freezing",
                        >= 0 and < 32 => "Freezing",
                        32 => "Exactly freezing",
                        >= 80 => "Hot",
                        _ => "Unknown"
                    };

                    return it;
                })
                .ToArray();

            return Task.FromResult(result);
        }
    }
}
