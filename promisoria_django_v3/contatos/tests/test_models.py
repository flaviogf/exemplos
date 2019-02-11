from django.test import TestCase

from contatos.models import Contato


class TestContato(TestCase):
    def test_contato_init(self):
        contato = Contato(nome='Flavio',
                          email='flavio@email.com',
                          telefone='11111111111')

        self.assertEquals('Flavio', contato.nome)
        self.assertEquals('flavio@email.com', contato.email)
        self.assertEquals('11111111111', contato.telefone)
