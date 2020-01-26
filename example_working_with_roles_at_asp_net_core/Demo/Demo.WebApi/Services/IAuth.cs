using System.Threading.Tasks;

namespace Demo.WebApi.Services
{
    public interface IAuth
    {
        Task<string> Attempt(string username, string password);
    }
}
