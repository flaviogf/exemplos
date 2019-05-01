import unittest

from hello_graphql_python.exemplo_01 import schema


class SchemaTests(unittest.TestCase):
    def test_should_hello_returns_hello_world_when_string_world_is_informed(self):
        query = '''
            {
                hello (name: "World")
            }
        '''

        result = schema.execute(query)

        self.assertEqual('Hello World', result.data['hello'])
