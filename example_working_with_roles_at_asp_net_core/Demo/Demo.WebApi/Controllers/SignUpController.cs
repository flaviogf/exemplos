using AutoMapper;
using Demo.WebApi.Models;
using Demo.WebApi.Repositories;
using Demo.WebApi.Services;
using Demo.WebApi.ViewModels;
using Demo.WebApi.ViewModels.SignUp;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace Demo.WebApi.Controllers
{
    [ApiController]
    [Route("api/sign-up")]
    public class SignUpController : ControllerBase
    {
        private readonly IUserRepository _userRepository;

        private readonly IMapper _mapper;

        private readonly IHash _hash;

        public SignUpController(IUserRepository userRepository, IHash hash, IMapper mapper)
        {
            _userRepository = userRepository;
            _mapper = mapper;
            _hash = hash;
        }

        [HttpPost]
        [Route("")]
        public async Task<IActionResult> Store([FromBody]SignUpStoreViewModel viewModel)
        {
            var passwordHash = await _hash.Make(viewModel.Password);

            var user = new User
            {
                Username = viewModel.Username,
                PasswordHash = passwordHash,
                Role = "user"
            };

            var created = _mapper.Map<UserViewModel>(await _userRepository.Add(user));

            await _userRepository.SaveChanges();

            return CreatedAtAction("Show", "User", new { id = created.Id }, created);
        }
    }
}
