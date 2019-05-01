import graphene


class Person(graphene.ObjectType):
    first_name = graphene.String()
    last_name = graphene.String()
    full_name = graphene.String()

    def resolve_full_name(self, info):
        return f'{self.first_name} {self.last_name}'


class Query(graphene.ObjectType):
    person = graphene.Field(Person)

    def resolve_person(self, info):
        return Person(first_name='Flavio', last_name='Fernandes')


schema = graphene.Schema(query=Query)
