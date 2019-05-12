import pandas
import requests

with open('avengers.csv', 'w') as file:
    file_url = 'https://raw.githubusercontent.com/fivethirtyeight/data/master/avengers/avengers.csv'
    response = requests.get(file_url)
    file.write(response.text)

with open('avengers.csv', 'r') as file:
    data_frame = pandas.read_csv(file)
