from django.test import TestCase

from contatos.forms import ContatoForm
from contatos.models import Contato


class TestContatoForm(TestCase):
    def test_contato_form_is_valid_true(self):
        request = {
            'nome': 'Flavio',
            'email': 'flavio@email.com',
            'telefone': '11111111111'
        }

        form = ContatoForm(request)

        self.assertTrue(form.is_valid())

    def test_contato_form_save(self):
        request = {
            'nome': 'Flavio',
            'email': 'flavio@email.com',
            'telefone': '11111111111'
        }

        form = ContatoForm(request)

        form.save()

        total_contatos = Contato.objects.count()
        contato_salvo = Contato.objects.first()

        self.assertEquals(1, total_contatos)
        self.assertEquals('Flavio', contato_salvo.nome)
        self.assertEquals('flavio@email.com', contato_salvo.email)
        self.assertEquals('11111111111', contato_salvo.telefone)
