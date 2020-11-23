namespace NewCsharpFeatures
{
    public class Person
    {
        public Person(int id, string firstName, string lastName)
        {
            Id = id;
            FirstName = firstName;
            LastName = lastName;
        }

        public Person()
        {
        }

        public int Id { get; init; }

        public string FirstName { get; init; }

        public string LastName { get; init; }

        public override string ToString() => $"Person[Id={Id}, FirstName={FirstName}, LastName={LastName}]";
    }
}