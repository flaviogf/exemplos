using Android.App;
using Android.Content;
using Android.Views;
using CustomRendererXF;
using CustomRendererXF.Droid;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(NovaPagina), typeof(NovaPaginaRenderer))]

namespace CustomRendererXF.Droid
{
    public class NovaPaginaRenderer : PageRenderer
    {
        private Android.Views.View _view;

        public NovaPaginaRenderer(Context context) : base(context)
        {
        }

        protected override void OnElementChanged(ElementChangedEventArgs<Page> e)
        {
            base.OnElementChanged(e);
            if (!(Context is Activity activity)) return;
            _view = activity.LayoutInflater.Inflate(Resource.Layout.NovaPagina, this, false);
            AddView(_view);
        }

        protected override void OnLayout(bool changed, int l, int t, int r, int b)
        {
            base.OnLayout(changed, l, t, r, b);

            var msw = MeasureSpec.MakeMeasureSpec(r - l, MeasureSpecMode.Exactly);
            var msh = MeasureSpec.MakeMeasureSpec(b - t, MeasureSpecMode.Exactly);

            _view.Measure(msw, msh);
            _view.Layout(0, 0, r - l, b - t);
        }
    }
}