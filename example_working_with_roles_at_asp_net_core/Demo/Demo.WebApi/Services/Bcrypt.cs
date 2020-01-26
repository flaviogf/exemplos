using System.Threading.Tasks;

namespace Demo.WebApi.Services
{
    public class Bcrypt : IHash
    {
        public Task<string> Make(string text)
        {
            var hash = BCrypt.Net.BCrypt.HashPassword(text);

            return Task.FromResult(hash);
        }

        public Task<bool> Verify(string text, string hash)
        {
            var result = BCrypt.Net.BCrypt.Verify(text, hash);

            return Task.FromResult(true);
        }
    }
}
