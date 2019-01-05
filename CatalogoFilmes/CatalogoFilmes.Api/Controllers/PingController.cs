using Microsoft.AspNetCore.Mvc;

namespace CatalogoFilmes.Api.Controllers
{

    [Route("api/[controller]")]
    public class PingController : Controller
    {

        public IActionResult OnGet()
        {
            return Ok("Api rodando");
        }
    }
}