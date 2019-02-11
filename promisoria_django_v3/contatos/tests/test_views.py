from django.test import Client, TestCase

from contatos.models import Contato


class TestContatoCreate(TestCase):
    def test_contato_create_get(self):
        client = Client()

        response = client.get('/contatos/novo/')

        self.assertEquals(200, response.status_code)
        self.assertIsNotNone(response.context['form'])

    def test_contato_create_post(self):
        request = {
            'nome': 'Flavio',
            'email': 'flavio@email.com',
            'telefone': '11111111111'
        }

        client = Client()

        response = client.post('/contatos/novo/', request)

        total_contatos = Contato.objects.count()

        self.assertEquals(302, response.status_code)
        self.assertEquals(1, total_contatos)


class TestContatoList(TestCase):
    def setUp(self):
        Contato.objects.create(nome='Flavio',
                               email='flavio@email.com',
                               telefone='11111111111')

    def test_contato_list_get(self):
        client = Client()

        response = client.get('/contatos/')

        total_contatos = len(response.context['contatos'])

        self.assertEquals(200, response.status_code)
        self.assertEquals(1, total_contatos)


class TestContatoUpdate(TestCase):
    def setUp(self):
        self.contato = Contato.objects.create(nome='Flavio',
                                              email='flavio@email.com',
                                              telefone='11111111111')

    def test_contato_update_get(self):
        client = Client()

        response = client.get(f'/contatos/{self.contato.contato_id}/atualiza/')

        self.assertEquals(200, response.status_code)
        self.assertEquals(self.contato.nome, response.context['contato'].nome)
        self.assertIsNotNone(response.context['form'])

    def test_contato_update_post(self):
        request = {
            'nome': 'atualizado',
            'email': 'atualizado@email.com',
            'telefone': 'atualizado'
        }

        client = Client()

        response = client.post(f'/contatos/{self.contato.contato_id}/atualiza/',
                               request)

        contato_atualizado = Contato.objects.get(
            contato_id=self.contato.contato_id)

        self.assertEquals(302, response.status_code)
        self.assertEquals(request['nome'], contato_atualizado.nome)
        self.assertEquals(request['email'], contato_atualizado.email)
        self.assertEquals(request['telefone'], contato_atualizado.telefone)
