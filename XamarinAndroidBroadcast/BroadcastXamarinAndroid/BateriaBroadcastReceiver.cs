using System;
using Android.Content;
using Android.OS;

namespace BroadcastXamarinAndroid
{
    [BroadcastReceiver(Enabled = true)]
    public class BateriaBroadcastReceiver : BroadcastReceiver
    {
        public event BateriaEventHandler Receive;

        public override void OnReceive(Context context, Intent intent)
        {
            var level = intent.GetIntExtra(BatteryManager.ExtraLevel, -1);
            var scale = intent.GetIntExtra(BatteryManager.ExtraScale, -1);
            var status = level / (float) scale;
            Receive?.Invoke(this, new BateriaEventArgs(status));
        }
    }

    public delegate void BateriaEventHandler(object sender, BateriaEventArgs e);

    public class BateriaEventArgs : EventArgs
    {
        public double Status { get; set; }

        public BateriaEventArgs(double status)
        {
            Status = status;
        }
    }
}