using Microsoft.Azure.Storage;
using Microsoft.Azure.Storage.Queue;
using System.Linq;

namespace Example.Sender
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var connectionString = "";

            var account = CloudStorageAccount.Parse(connectionString);

            var client = account.CreateCloudQueueClient();

            var queue = client.GetQueueReference("myqueue");

            foreach (var number in Enumerable.Range(0, 5))
            {
                var message = new CloudQueueMessage($"Message nº {number}");

                queue.AddMessage(message);
            }
        }
    }
}
