using System;
using System.IO;
using System.Text;

namespace Example001.ReadingTextFiles
{
    public class Program
    {
        public static void Main(string[] args)
        {
            using var file = new FileStream("contas.txt", FileMode.Open, FileAccess.Read);

            var encoding = Encoding.UTF8;

            var buffer = new byte[1024];

            var bytesReaded = -1;

            while ((bytesReaded = file.Read(buffer, 0, 1024)) != 0)
            {
                Console.WriteLine(encoding.GetString(buffer, 0, bytesReaded));
            }
        }
    }
}
