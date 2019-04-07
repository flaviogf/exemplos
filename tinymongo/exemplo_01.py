from tinymongo import TinyMongoClient

connection = TinyMongoClient()

db = connection.db

users = db.users

users.insert_one({'nome': 'flavio'})
