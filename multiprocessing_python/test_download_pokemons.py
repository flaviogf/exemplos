import unittest
from os.path import isdir, isfile, join
from shutil import rmtree

from download_pokemons import (POKE_DIR, Pokemon, download_sprite_of,
                               get_url_sprite_for, list_pokemons)


class ListPokemonsTests(unittest.TestCase):
    def test_should_returns_list_with_100_pokemons(self):
        pokemons = list_pokemons()

        self.assertEqual(100, len(pokemons))


class GetUrlSpriteForTests(unittest.TestCase):
    def test_should_return_a_pokemon_with_sprite(self):
        url = 'https://pokeapi.co/api/v2/pokemon/1'

        bulbasaur = Pokemon(name='bulbasaur', url=url)

        bulbasaur = get_url_sprite_for(bulbasaur)

        expected = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png'

        self.assertEqual(expected, bulbasaur.sprite)


class DownloadSpriteOfTests(unittest.TestCase):
    def test_should_download_and_save_sprite(self):
        sprite = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png'

        bulbasaur = Pokemon(name='bulbasaur', sprite=sprite)

        download_sprite_of(bulbasaur)

        bulbasaur_sprite = join('pokemons', f'{bulbasaur.name}.png')

        self.assertTrue(isfile(bulbasaur_sprite))

    def tearDown(self):
        if isdir(POKE_DIR):
            rmtree(POKE_DIR)
