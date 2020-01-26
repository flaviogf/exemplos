using Demo.WebApi.Repositories;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;

namespace Demo.WebApi.Services
{
    public class Jwt : IAuth
    {
        private readonly IUserRepository _userRepository;

        private readonly IConfiguration _configuration;

        private readonly IHash _hash;

        public Jwt(IUserRepository userRepository, IConfiguration configuration, IHash hash)
        {
            _userRepository = userRepository;
            _configuration = configuration;
            _hash = hash;
        }

        public async Task<string> Attempt(string username, string password)
        {
            var user = await _userRepository.GetByUsername(username);

            if (user == null || !(await _hash.Verify(password, user.PasswordHash)))
            {
                return null;
            }

            var handler = new JwtSecurityTokenHandler();

            var descriptor = new SecurityTokenDescriptor()
            {
                Subject = new ClaimsIdentity(new Claim[]
                {
                    new Claim(ClaimTypes.Name, user.Username),
                    new Claim(ClaimTypes.Role, user.Role)
                }),
                Expires = DateTime.Now.AddDays(30),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Authorization:JwtBearer:SecretKey"])), SecurityAlgorithms.HmacSha256)
            };

            var token = handler.WriteToken(handler.CreateToken(descriptor));

            return token;
        }
    }
}
