using System;
using System.IO;

namespace Example002.MoreFileStreamAndStreamReader
{
    public class Program
    {
        public static void Main(string[] args)
        {
            using (var file = new FileStream("contas.txt", FileMode.Open, FileAccess.Read))
            using (var reader = new StreamReader(file))
            {
                while (!reader.EndOfStream)
                {
                    Console.WriteLine(reader.ReadLine());
                }
            }
        }
    }
}
