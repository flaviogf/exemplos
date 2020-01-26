using Demo.WebApi.Repositories;
using Demo.WebApi.Services;
using Demo.WebApi.ViewModels.SignIn;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace Demo.WebApi.Controllers
{
    [ApiController]
    [Route("api/sign-in")]
    public class SignInController : ControllerBase
    {
        private readonly IUserRepository _userRepository;

        private readonly IAuth _auth;

        public SignInController(IUserRepository userRepository, IAuth auth)
        {
            _userRepository = userRepository;
            _auth = auth;
        }

        [HttpPost]
        [Route("")]
        public async Task<IActionResult> Store([FromBody]SignInStoreViewModel viewModel)
        {
            var token = await _auth.Attempt(viewModel.Username, viewModel.Password);

            if (token == null)
            {
                return Unauthorized();
            }

            return Ok(token);
        }
    }
}
