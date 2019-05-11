import os

import requests

API_KEY = os.getenv('API_KEY')
AUTH_FIREBASE = f'https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyPassword?key={API_KEY}'

data = {
    'email': 'flavio.fernandes6@gmail.com',
    'password': 'teste123!',
    'returnSecureToken': True,
}

response = requests.post(AUTH_FIREBASE, data=data)

print(response)
