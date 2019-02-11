from http import HTTPStatus

from django.test import Client, TestCase

from contatos.models import Contato


class TestContatoCreate(TestCase):
    def test_contato_create_get(self):
        url = '/contatos/criar/'

        client = Client()

        response = client.get(url)

        self.assertEquals(HTTPStatus.OK, response.status_code)

    def test_contato_create_post(self):
        url = '/contatos/criar/'

        request = {
            'nome': 'Flavio',
            'email': 'flavio@email.com',
            'telefone': '11111111111'
        }

        client = Client()

        response = client.post(url, request)

        self.assertEquals(HTTPStatus.FOUND, response.status_code)


class TestContatoList(TestCase):
    def setUp(self):
        self.contato = Contato.objects.create(nome='Flavio',
                                              email='flavio@email.com',
                                              telefone='11111111111')

    def test_contato_list_get(self):
        url = '/contatos/'

        client = Client()

        response = client.get(url)

        self.assertEquals(HTTPStatus.OK, response.status_code)


class TestContatoUpdate(TestCase):
    def setUp(self):
        self.contato = Contato.objects.create(nome='Flavio',
                                              email='flavio@email.com',
                                              telefone='11111111111')

    def test_contato_update_get(self):
        url = f'/contatos/{self.contato.contato_id}/atualizar/'

        client = Client()

        response = client.get(url)

        self.assertEquals(HTTPStatus.OK, response.status_code)

    def test_contato_update_post(self):
        url = f'/contatos/{self.contato.contato_id}/atualizar/'

        request = {
            'nome': 'atualizado',
            'email': 'atualizado@email.com',
            'telefone': 'atualizado'
        }

        client = Client()

        response = client.post(url, request)

        self.assertEquals(HTTPStatus.FOUND, response.status_code)


class TestContatoDelete(TestCase):
    def setUp(self):
        self.contato = Contato.objects.create(nome='Flavio',
                                              email='flavio@email.com',
                                              telefone='11111111111')

    def test_contato_delete_get(self):
        url = f'/contatos/{self.contato.contato_id}/deletar/'

        client = Client()

        response = client.get(url)

        self.assertEquals(HTTPStatus.OK, response.status_code)

    def test_contato_delete_post(self):
        url = f'/contatos/{self.contato.contato_id}/deletar/'

        client = Client()

        response = client.post(url)

        self.assertEquals(HTTPStatus.FOUND, response.status_code)
