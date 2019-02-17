using Xamarin.Forms;

namespace CustomRendererXF
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();
            MainPage = new NovaPagina();
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}