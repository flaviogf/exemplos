from datetime import datetime, timedelta
from random import choice
from typing import List, Generator


class DescricaoDesafio:
    def __init__(self,
                 titulo: 'str',
                 descricao: 'str'):
        self._titulo = titulo
        self._descricao = descricao

    @property
    def titulo(self) -> 'str':
        return self._titulo

    @property
    def descricao(self) -> 'str':
        return self._descricao


class Campanha:
    def __init__(self,
                 nome: 'str',
                 data_inicio: 'datetime',
                 data_fim: 'datetime'):
        self._nome = nome
        self._data_inicio = data_inicio
        self._data_fim = data_fim

    @property
    def data_inicio(self) -> 'datetime':
        return self._data_inicio

    @property
    def data_fim(self) -> 'datetime':
        return self._data_fim

    @property
    def total_de_dias(self) -> 'int':
        return (self._data_fim - self._data_inicio).days + 1


class Desafio:
    def __init__(self,
                 titulo: 'str',
                 descricao: 'str',
                 data_inicio: 'datetime',
                 data_fim: 'datetime'):
        self._titulo = titulo
        self._descricao = descricao
        self._data_inicio = data_inicio
        self._data_fim = data_fim

    @property
    def data_inicio(self) -> 'datetime':
        return self._data_inicio

    @property
    def data_fim(self) -> 'datetime':
        return self._data_fim


class GeradorDesafios:
    def __init__(self, campanha: 'Campanha', descricoes_desafios: 'List[DescricaoDesafio]'):
        self._campanha = campanha
        self._descricoes_desafios = descricoes_desafios
        self._dias_a_adicionar = 0
        self._data_inicio = campanha.data_inicio
        self._data_fim = campanha.data_inicio
        self._descricao_desafio = None

    def __iter__(self):
        return self.gera()

    def gera(self) -> 'Generator[Desafio]':
        for indice in range(self._campanha.total_de_dias):
            self._dias_a_adicionar = indice
            self._sorteia_descricao_desafio()
            self._gera_data_inicio()
            self._gera_data_fim()

            yield Desafio(titulo=self._descricao_desafio.titulo,
                          descricao=self._descricao_desafio.descricao,
                          data_inicio=self._data_inicio,
                          data_fim=self._data_fim)

    def _sorteia_descricao_desafio(self) -> 'None':
        self._descricao_desafio = choice(self._descricoes_desafios)

    def _gera_data_inicio(self) -> 'None':
        self._data_inicio = self._campanha.data_inicio + timedelta(days=self._dias_a_adicionar)

    def _gera_data_fim(self) -> 'None':
        self._data_fim = self._data_inicio + timedelta(days=1)
