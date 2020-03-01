using Microsoft.Azure.Storage;
using Microsoft.Azure.Storage.Queue;
using System;

namespace Example.Receiver
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var connectionString = "";

            var account = CloudStorageAccount.Parse(connectionString);

            var client = account.CreateCloudQueueClient();

            var queue = client.GetQueueReference("myqueue");

            CloudQueueMessage message;

            while ((message = queue.GetMessage()) != null)
            {
                Console.WriteLine("Message was received.");
                Console.WriteLine(message.AsString);

                queue.DeleteMessage(message);
            }
        }
    }
}
