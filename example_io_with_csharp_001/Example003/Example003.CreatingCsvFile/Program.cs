using System.IO;
using System.Text;

namespace Example003.CreatingCsvFile
{
    public class Program
    {
        public static void Main(string[] args)
        {
            WithoutStreamWriter();

            WithStreamWriter();
        }

        private static void WithStreamWriter()
        {
            using (var file = new FileStream("accounts.csv", FileMode.Append, FileAccess.Write))
            using (var writer = new StreamWriter(file))
            {
                writer.WriteLine("000,000-0,1800.00,Flavio");
            }
        }

        private static void WithoutStreamWriter()
        {
            using (var file = new FileStream("accounts.csv", FileMode.Create, FileAccess.Write))
            {
                var account = "000,000-0,1800.00,Flavio\n";
                var buffer = Encoding.UTF8.GetBytes(account);
                file.Write(buffer, 0, buffer.Length);
            }
        }
    }
}
