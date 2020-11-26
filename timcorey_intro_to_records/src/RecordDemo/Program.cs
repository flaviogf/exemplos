using System;

namespace RecordDemo
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
        }
    }

    public record Record1(string FirstName, string LastName);
}
