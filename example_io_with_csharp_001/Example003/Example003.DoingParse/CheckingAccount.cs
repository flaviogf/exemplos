namespace Example003.DoingParse
{
    public class CheckingAccount
    {
        public CheckingAccount(string agency, string number, decimal balance, string owner)
        {
            Agency = agency;
            Number = number;
            Balance = balance;
            Owner = owner;
        }

        public string Agency { get; set; }

        public string Number { get; set; }

        public decimal Balance { get; set; }

        public string Owner { get; set; }

        public override string ToString()
        {
            return $"CheckinAccount[Agency={Agency}, Number={Number}, Balance={Balance:C}, Owner={Owner}]";
        }
    }
}
