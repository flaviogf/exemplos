using Microsoft.AspNetCore.Mvc;

namespace Todo.Web.Areas.Admin.Controllers
{
    [Area("admin")]
    [Route("[area]")]
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