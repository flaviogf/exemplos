using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

namespace SpinTask.Cli
{
    public class Program
    {
        public static IEnumerable<Char> Cycle(string iterable)
        {
            while (true)
            {
                foreach (var element in iterable)
                {
                    yield return element;
                }
            }
        }

        public static Action Spin(CancellationToken token) => () =>
        {
            foreach (var item in Cycle(@"/|-\"))
            {
                if (token.IsCancellationRequested)
                {
                    token.ThrowIfCancellationRequested();
                    break;
                }

                Console.Write($"{item} \r");

                Thread.Sleep(1000);
            }
        };


        public static void DownloadAnyImage()
        {
            Thread.Sleep(10000);

            Console.WriteLine();

            Console.WriteLine("Image downloaded.");
        }

        public static void Main(string[] args)
        {
            var tokenSource = new CancellationTokenSource();

            var token = tokenSource.Token;

            var spinTask = Task.Run(Spin(token), token);

            DownloadAnyImage();

            tokenSource.Cancel();
        }
    }
}
