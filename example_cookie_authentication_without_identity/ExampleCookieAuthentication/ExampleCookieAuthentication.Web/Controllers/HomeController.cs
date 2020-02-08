using Microsoft.AspNetCore.Mvc;

namespace ExampleCookieAuthentication.Web.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
