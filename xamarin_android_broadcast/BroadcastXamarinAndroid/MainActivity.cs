using System;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Widget;

namespace BroadcastXamarinAndroid
{
    [Activity(
        Label = "@string/app_name",
        MainLauncher = true
    )]
    public class MainActivity : Activity
    {
        private TextView _textHorario;
        private TextView _textStatus;

        private BateriaBroadcastReceiver _bateriaReceiver;
        private TimeBroadcastReceiver _timeReceiver;

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            SetContentView(Resource.Layout.Main);
            _textHorario = FindViewById<TextView>(Resource.Id.TextHorario);
            _textStatus = FindViewById<TextView>(Resource.Id.TextStatus);
            Inicializa();
        }

        protected override void OnDestroy()
        {
            base.OnDestroy();
            UnregisterReceiver(_bateriaReceiver);
            UnregisterReceiver(_timeReceiver);
        }

        private void Inicializa()
        {
            EscutaBateria();
            EscutaHorario();
        }

        private void EscutaHorario()
        {
            _textHorario.Text = $"{DateTime.Now:t}";
            _timeReceiver = new TimeBroadcastReceiver();
            _timeReceiver.Receive += (o, e) => _textHorario.Text = $"{e.Time:t}";
            RegisterReceiver(_timeReceiver, new IntentFilter(Intent.ActionTimeTick));
        }

        private void EscutaBateria()
        {
            _bateriaReceiver = new BateriaBroadcastReceiver();
            _bateriaReceiver.Receive += (o, e) => _textStatus.Text = $"{e.Status:P}";
            RegisterReceiver(_bateriaReceiver, new IntentFilter(Intent.ActionBatteryChanged));
        }
    }
}