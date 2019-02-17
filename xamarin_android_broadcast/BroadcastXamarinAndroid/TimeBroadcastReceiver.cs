using System;
using Android.Content;

namespace BroadcastXamarinAndroid
{
    [BroadcastReceiver(Enabled = true)]
    public class TimeBroadcastReceiver : BroadcastReceiver
    {
        public event TimeEventHandler Receive;

        public override void OnReceive(Context context, Intent intent)
        {
            Receive?.Invoke(this, new TimeEventArgs(DateTime.Now));
        }
    }

    public delegate void TimeEventHandler(object sender, TimeEventArgs e);

    public class TimeEventArgs : EventArgs
    {
        public DateTime Time { get; set; }

        public TimeEventArgs(DateTime time)
        {
            Time = time;
        }
    }
}