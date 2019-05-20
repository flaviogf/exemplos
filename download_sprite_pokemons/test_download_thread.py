import unittest
from os.path import dirname, exists, isfile, join
from shutil import rmtree

from download_thread import (DownloadPokemonSprite, GetPokemonSprite,
                             ListPokemons, Pokemon)


class ListPokemonsTests(unittest.TestCase):
    def setUp(self):
        self._list_pokemons = ListPokemons()

    def test_should_list_pokemons_returns_all_pokemons(self):
        pokemons = self._list_pokemons.all()

        self.assertEqual(100, len(pokemons))

    def test_should_all_itens_in_list_is_instance_of_pokemon(self):
        with self.subTest():
            for pokemon in self._list_pokemons.all():
                self.assertIsInstance(pokemon, Pokemon)


class GetPokemonSpriteTests(unittest.TestCase):
    def test_should_return_sprite_pokemon_url(self):
        get_pokemon_sprite = GetPokemonSprite()

        charmander = Pokemon(id=4, name='charmander')

        charmander = get_pokemon_sprite.front_default(charmander)

        sprite_url_expected = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png'

        self.assertEqual(sprite_url_expected, charmander.sprite)


class DownloadPokemonSpriteTests(unittest.TestCase):
    def test_should_download_pokemon_sprite_create_file_png_in_folder_sprites(self):
        charmander = Pokemon(id=4,
                             name='charmander',
                             sprite='https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png')

        download_pokemon_sprite = DownloadPokemonSprite()

        filename = download_pokemon_sprite.of(charmander)

        self.assertTrue(isfile(filename))

    def tearDown(self):
        sprites_folder = join(dirname(__file__), 'sprites')

        if exists(sprites_folder):
            rmtree(sprites_folder)
