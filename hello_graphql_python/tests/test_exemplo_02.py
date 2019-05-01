import unittest

from hello_graphql_python.exemplo_02 import Person, schema


class PersonTests(unittest.TestCase):
    def test_should_returns_full_name(self):
        query = '''
            query {
                person {
                    fullName
                }
            }
        '''

        flavio = Person(first_name='Flavio', last_name='Fernandes')

        context = {'person': flavio}

        result = schema.execute(query, context=context)

        full_name = result.data['person']['fullName']

        self.assertEqual(full_name, 'Flavio Fernandes')
