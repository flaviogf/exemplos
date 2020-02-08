using System.ComponentModel.DataAnnotations;

namespace ExampleCookieAuthentication.Web.ViewModels
{
    public class SignInViewModel
    {
        [Required]
        public string Username { get; set; }

        [Required]
        [DataType(DataType.Password)]
        public string Password { get; set; }
    }
}
