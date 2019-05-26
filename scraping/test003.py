import csv

from bs4 import BeautifulSoup
from selenium.webdriver import Firefox, FirefoxOptions

options = FirefoxOptions()
options.headless = True

browser = Firefox(options=options)

browser.get('http://smn.com.br')

while True:
    try:
        browser.find_element_by_css_selector('.smn-perfil-nome')
        break
    except Exception:
        pass


bs = BeautifulSoup(browser.page_source, 'html.parser')

with open('employes.csv', 'w', newline='') as file:
    csv_writer = csv.writer(file, delimiter=',')
    csv_writer.writerow(['photo', 'name', 'role'])
    for employe in bs.find('div', {'class': 'smn-equipe-wrap'}).find_all('figure'):
        photo = employe.find('img')['src']
        photo = f'http://smn.com.br/{photo}'
        name = employe.find('span', {'class': 'smn-perfil-nome'})
        name = name.get_text().strip() if name else ''
        role = employe.find('span', {'class': 'smn-perfil-area'})
        role = role.get_text().strip() if role else ''
        csv_writer.writerow([photo, name, role])

browser.close()
