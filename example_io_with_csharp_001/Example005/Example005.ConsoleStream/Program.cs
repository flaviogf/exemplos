using System;
using System.IO;

namespace Example005.ConsoleStream
{
    public class Program
    {
        public static void Main(string[] args)
        {
            using (var stream = Console.OpenStandardInput())
            using (var file = new FileStream("output.txt", FileMode.Create, FileAccess.Write))
            {
                while(true)
                {
                    var buffer = new byte[1024];

                    var readed = stream.Read(buffer, 0, 1024);

                    file.Write(buffer, 0, readed);

                    file.Flush();

                    Console.WriteLine($"Bytes readed {readed}");
                }
            }
        }
    }
}
