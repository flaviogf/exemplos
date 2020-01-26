using AutoMapper;
using Demo.WebApi.Repositories;
using Demo.WebApi.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Demo.WebApi.Controllers
{
    [ApiController]
    [Route("api/user")]
    public class UserController : ControllerBase
    {
        private readonly IUserRepository _userRepository;

        private readonly IMapper _mapper;

        public UserController(IUserRepository userRepository, IMapper mapper)
        {
            _userRepository = userRepository;
            _mapper = mapper;
        }

        [HttpGet]
        [Route("")]
        [Authorize(Roles = "staff")]
        public async Task<IActionResult> Index()
        {
            var users = _mapper.Map<IEnumerable<UserViewModel>>(await _userRepository.GetAll());

            return Ok(users);
        }

        [HttpGet]
        [Route("{id}")]
        [Authorize(Roles = "staff")]
        public async Task<IActionResult> Show(Guid id)
        {
            var user = _mapper.Map<UserViewModel>(await _userRepository.Get(id));

            return Ok(user);
        }
    }
}
