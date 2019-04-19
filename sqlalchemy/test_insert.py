import unittest

from dao import UserDAO
from db import engine, metadata, users


class UserDAOTests(unittest.TestCase):
    def setUp(self):
        metadata.create_all(engine)
        self._conn = engine.connect()

    def tearDown(self):
        self._conn.close()
        metadata.drop_all(engine)

    def test_insert(self):
        dao = UserDAO(self._conn, users)

        user = {
            'name': 'Flavio',
            'fullname': 'Flavio Fernandes',
        }

        dao.insert(user)

    def test_select(self):
        dao = UserDAO(self._conn, users)

        user = {
            'name': 'Flavio',
            'fullname': 'Flavio Fernandes',
        }

        dao.insert(user)

        users_list = dao.list()

        _, name, fullname = users_list[0]

        self.assertEqual(1, len(users_list))
        self.assertEqual('Flavio', name)
        self.assertEqual('Flavio Fernandes', fullname)
