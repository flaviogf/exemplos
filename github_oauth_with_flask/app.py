import requests
from flask import Flask, render_template, request

from os import environ

from urllib.parse import parse_qs

CLIENT_ID = environ.get('CLIENT_ID')
CLIENT_SECRET = environ.get('CLIENT_SECRET')

app = Flask(__name__)


@app.route('/')
def login():
    api_url = 'https://github.com/login/oauth/authorize'

    args = f'?scope=user:email&client_id={CLIENT_ID}'

    request_link = f'{api_url}{args}'

    return render_template('login.html', request_link=request_link)


@app.route('/callback')
def callback():
    code = request.args.get('code')

    data = {
        'client_id': CLIENT_ID,
        'client_secret': CLIENT_SECRET,
        'code': code
    }

    response = requests.post(url='https://github.com/login/oauth/access_token',
                             data=data)

    access_token = parse_qs(response.text)['access_token'][0]

    response = requests.get(
        url=f'https://api.github.com/user?access_token={access_token}')

    auth_result = response.json()

    return render_template('index.html', user=auth_result.get('login'))


if __name__ == '__main__':
    app.run(debug=True)
