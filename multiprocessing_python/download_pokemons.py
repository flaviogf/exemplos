from collections import namedtuple
from os import mkdir
from os.path import dirname, exists, join

import requests

POKE_API = 'https://pokeapi.co/api/v2/pokemon?limit=100'
POKE_DIR = join(dirname(__file__), 'pokemons')


Pokemon = namedtuple('Pokemon',
                     ['name', 'url', 'sprite'],
                     defaults=[None, None, None])


def list_pokemons():
    pokemons = requests.get(POKE_API).json()['results']
    return [Pokemon(it['name'], it['url']) for it in pokemons]


def get_url_sprite_for(pokemon):
    sprite = requests.get(pokemon.url).json()['sprites']['front_default']
    return Pokemon(pokemon.name, pokemon.url, sprite)


def download_sprite_of(pokemon):
    if not exists(POKE_DIR):
        mkdir(POKE_DIR)

    filename = join(POKE_DIR, f'{pokemon.name}.png')

    with open(filename, 'wb') as f:
        for chunk in requests.get(pokemon.sprite).iter_content(chunk_size=128):
            f.write(chunk)

    print(pokemon, 'downloaded')

    return pokemon


class Pipeline:
    def __init__(self, *funcs):
        self._funcs = funcs

    def __call__(self, args):
        result = args

        for func in self._funcs:
            result = func(result)

        return result


target = Pipeline(get_url_sprite_for, download_sprite_of)


if __name__ == '__main__':
    from multiprocessing import Pool

    with Pool(15) as worker:
        result = worker.map_async(target, list_pokemons())

        result.wait()
