using System;
using System.IO;

namespace Example004.TheFlushAndBinaryFiles
{
    public class Program
    {
        public static void Main(string[] args)
        {
            UsingBinary();
            UsingFlush();
        }

        private static void UsingBinary()
        {
            using (var file = new FileStream("temp_002.txt", FileMode.Create, FileAccess.Write))
            using (var writer = new BinaryWriter(file))
            {
                writer.Write(1);
                writer.Write(true);
                writer.Write(false);
                writer.Write(10.0);
            }

            using (var file = new FileStream("temp_002.txt", FileMode.Open, FileAccess.Read))
            using (var reader = new BinaryReader(file))
            {
                Console.WriteLine(reader.ReadInt32());
                Console.WriteLine(reader.ReadBoolean());
                Console.WriteLine(reader.ReadBoolean());
                Console.WriteLine(reader.ReadDouble());
            }
        }

        private static void UsingFlush()
        {
            Console.CancelKeyPress += (sender, eventArgs) =>
            {
                Environment.Exit(0);
            };

            using (var file = new FileStream("temp.txt", FileMode.Create, FileAccess.Write))
            using (var writer = new StreamWriter(file))
            {
                while (true)
                {
                    var textWrited = Console.ReadLine();

                    writer.WriteLine(textWrited);
                    writer.Flush();
                }
            }
        }
    }
}
