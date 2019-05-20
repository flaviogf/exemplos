import unittest

from download_thread import GetSpritePokemon, ListPokemons, Pokemon


class ListPokemonsTests(unittest.TestCase):
    def setUp(self):
        self._list_pokemons = ListPokemons()

    def test_should_list_pokemons_returns_all_pokemons(self):
        pokemons = self._list_pokemons.all()

        self.assertEqual(10, len(pokemons))

    def test_should_all_itens_in_list_is_instance_of_pokemon(self):
        with self.subTest():
            for pokemon in self._list_pokemons.all():
                self.assertIsInstance(pokemon, Pokemon)


class GetSpritePokemonTests(unittest.TestCase):
    def test_should_return_sprite_pokemon_url(self):
        get_sprite_pokemon = GetSpritePokemon()

        charmander = Pokemon(id=4, name='charmander')

        charmander = get_sprite_pokemon.front_default(charmander)

        sprite_url_expected = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png'

        self.assertEqual(sprite_url_expected, charmander.sprite)
