from sqlalchemy import (Column, ForeignKey, Integer, MetaData, String, Table,
                        create_engine)

engine = create_engine('sqlite:///:memory:', echo=True)

metadata = MetaData()

users = Table(
    'users',
    metadata,
    Column('id', Integer, primary_key=True),
    Column('name', String),
    Column('fullname', String),
)

addresses = Table(
    'addresses',
    metadata,
    Column('id', Integer, primary_key=True),
    Column('user_id', None, ForeignKey('users.id')),
)
