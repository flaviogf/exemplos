using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;

namespace Example003.DoingParse
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CultureInfo.CurrentCulture = new CultureInfo("en-US", false);

            foreach (var item in ReadFile())
            {
                Console.WriteLine(item);
            }
        }

        public static IEnumerable<CheckingAccount> ReadFile()
        {
            using (var file = new FileStream("contas.txt", FileMode.Open, FileAccess.Read))
            using (var reader = new StreamReader(file))
            {
                while (!reader.EndOfStream)
                {
                    var line = reader.ReadLine().Split(',');
                    var agency = line[0];
                    var number = line[1];
                    var balance = decimal.Parse(line[2]);
                    var owner = line[3];
                    yield return new CheckingAccount(agency, number, balance, owner);
                }
            }
        }
    }
}
