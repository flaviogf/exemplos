using System;
using NewCsharpFeatures;

Console.WriteLine("Hello World");

Console.WriteLine(Add(10, 10));

var frank = new Person { Id = 1, FirstName = "Frank", LastName = "Fernandes" };

Console.WriteLine(frank);

// frank.FirstName = "Something"; doesn't work

Person nina = new(id: 2, firstName: "Nina", lastName: "Fernandes");

Console.WriteLine(nina);

double Add(double x, double y)
{
    return x + y;
}