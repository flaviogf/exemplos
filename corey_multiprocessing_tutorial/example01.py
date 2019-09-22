import time
from concurrent.futures import ProcessPoolExecutor

import requests


def get_sprite_url(pokemon_url):
    return requests.get(pokemon_url).json()['sprites']['front_default']


def download_sprite(sprite_url):
    image = requests.get(sprite_url).content
    filename = sprite_url.split('/')[-1]
    with open(f'images/{filename}', 'wb') as file:
        file.write(image)
        return f'{filename} saved successfully.'


start = time.perf_counter()

with ProcessPoolExecutor() as executor:
    urls = [f'https://pokeapi.co/api/v2/pokemon/{i + 1}' for i in range(20)]

    def task(pokemon_url):
        return download_sprite(get_sprite_url(pokemon_url))

    results = executor.map(task, urls)

    for result in results:
        print(result)

end = time.perf_counter()

print(f'Finished in {round(end - start, 2)} second(s)')
