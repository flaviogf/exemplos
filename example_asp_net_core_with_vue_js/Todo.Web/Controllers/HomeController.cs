using Microsoft.AspNetCore.Mvc;

namespace Todo.Web.Controllers
{
    [Route("")]
    public class HomeController : Controller
    {
        [HttpGet]
        [Route("")]
        public IActionResult Index()
        {
            return View();
        }
    }
}