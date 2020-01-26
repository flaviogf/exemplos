using System.Threading.Tasks;

namespace Demo.WebApi.Services
{
    public interface IHash
    {
        Task<string> Make(string text);

        Task<bool> Verify(string text, string hash);
    }
}
