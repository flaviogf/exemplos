import requests
from bs4 import BeautifulSoup

response = requests.get('http://smn.com.br')

bs = BeautifulSoup(response.text, 'html.parser')
