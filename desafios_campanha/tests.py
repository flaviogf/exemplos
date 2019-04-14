import unittest
from datetime import datetime, timedelta
from typing import List

from desafios import GeradorDesafios, DescricaoDesafio, Campanha


def gera_descricoes_desafios() -> 'List[DescricaoDesafio]':
    return [DescricaoDesafio(titulo=f'Desafio {it}',
                             descricao=f'desafio {it} ... !!!') for it in range(1, 10)]


def gera_data_inicio() -> 'datetime':
    return datetime.now() - timedelta(days=10)


def gera_data_fim() -> datetime:
    return datetime.now() + timedelta(days=10)


class GeradoDesafiosTests(unittest.TestCase):
    def test_gera(self):
        descricoes_desafios = gera_descricoes_desafios()
        data_inicio = gera_data_inicio()
        data_fim = gera_data_fim()

        campanha = Campanha(nome='Pascoa',
                            data_inicio=data_inicio,
                            data_fim=data_fim)

        sut = GeradorDesafios(campanha=campanha,
                              descricoes_desafios=descricoes_desafios)

        for indice, desafio in enumerate(sut):
            with self.subTest():
                data_inicio_esperada = data_inicio + timedelta(days=indice)
                data_fim_esperada = data_inicio + timedelta(days=indice + 1)
                self.assertEqual(data_inicio_esperada, desafio.data_inicio)
                self.assertEqual(data_fim_esperada, desafio.data_fim)


if __name__ == '__main__':
    unittest.main()
