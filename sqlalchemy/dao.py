from sqlalchemy import select


class UserDAO:
    def __init__(self, conn, users):
        self._conn = conn
        self._users = users

    def insert(self, user):
        ins = self._users.insert()

        self._conn.execute(ins,
                           name=user['name'],
                           fullname=user['fullname'])

    def list(self):
        rows = self._conn.execute(select([self._users]))
        return rows.fetchall()
