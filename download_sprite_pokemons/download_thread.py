from dataclasses import dataclass
from datetime import datetime
from os import mkdir
from os.path import dirname, exists, join
from queue import Queue
from threading import Thread

import requests

API_URL = 'https://pokeapi.co/api/v2/'


@dataclass
class Pokemon:
    id: int
    name: str
    sprite: str = None


class ListPokemons:
    def all(self):
        response = requests.get(f'{API_URL}pokemon?limit=100')

        response = response.json()

        return [Pokemon(id=id, name=pokemon['name'])
                for id, pokemon in enumerate(response['results'], 1)]


class GetPokemonSprite:
    def front_default(self, pokemon):
        response = requests.get(f'{API_URL}pokemon/{pokemon.id}')

        response = response.json()

        sprite = response['sprites']['front_default']

        return Pokemon(id=pokemon.id, name=pokemon.name, sprite=sprite)


class DownloadPokemonSprite:
    def __init__(self):
        self._folder_sprites = join(dirname(__file__), 'sprites')

        if not exists(self._folder_sprites):
            mkdir(self._folder_sprites)

    def of(self, pokemon):
        filename = join(self._folder_sprites, f'{pokemon.name}.png')

        sprite = requests.get(pokemon.sprite).content

        with open(filename, 'wb') as file:
            file.write(sprite)

        return filename


def timeit(label=''):
    def decorator(func):
        def inner(*args, **kwargs):
            start_time = datetime.now()
            result = func(*args, **kwargs)
            end_time = datetime.now()
            print(f'{label}: {end_time - start_time}')
            return result
        return inner

    return decorator


@timeit(label='main')
def main():
    queue = Queue()

    def get_sprite_of_list_pokemon(pokemons):
        get_pokemon_sprite = GetPokemonSprite()
        return [get_pokemon_sprite.front_default(pokemon) for pokemon in pokemons]

    def get_first_half():
        list_pokemons = ListPokemons()
        download_sprite_pokemons = DownloadPokemonSprite()

        pokemons = list_pokemons.all()

        half_pokemons = len(pokemons) // 2

        pokemons = get_sprite_of_list_pokemon(pokemons[:half_pokemons])

        for pokemon in pokemons:
            download_sprite_pokemons.of(pokemon)

    def get_second_half():
        list_pokemons = ListPokemons()
        download_sprite_pokemons = DownloadPokemonSprite()

        pokemons = list_pokemons.all()

        half_pokemons = len(pokemons) // 2

        pokemons = get_sprite_of_list_pokemon(pokemons[half_pokemons:])

        for pokemon in pokemons:
            download_sprite_pokemons.of(pokemon)

    first_thread = Thread(name='first_half', target=get_first_half)
    second_thread = Thread(name='second_half', target=get_second_half)

    first_thread.start()
    second_thread.start()

    first_thread.join()
    second_thread.join()

    while not queue.empty():
        print(queue.get())


if __name__ == '__main__':
    main()
